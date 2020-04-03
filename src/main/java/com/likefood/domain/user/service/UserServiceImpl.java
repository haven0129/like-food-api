package com.likefood.domain.user.service;


import com.likefood.domain.user.model.LoginLog;
import com.likefood.domain.user.model.User;
import com.likefood.domain.user.model.UserRole;
import com.likefood.domain.user.repository.LoginLogRepository;
import com.likefood.domain.user.repository.UserRepository;
import com.likefood.domain.user.repository.UserSpecs;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.user.UserDtoC;
import com.likefood.pojo.user.UserDtoS;
import com.likefood.pojo.user.UserDtoU;
import com.likefood.pojoconverter.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginLogRepository loginLogRepository;
    @Autowired
    private UserDtoConverter userDtoConverter;
    @Autowired
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public User saveUserFirst(UserDtoC userDtoC) throws DataAccessException {
        User souce = userDtoConverter.buildUser(userDtoC);
        User result = save(souce);
        for(Long roleId : userDtoC.getRoleIds()){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(result.getId());
            roleService.saveUserRole(userRole);
        }
        return result;
    }

    @Override
    public User updateUser(User user, UserDtoU userDtoU) throws DataAccessException {
        user = userDtoConverter.buildUser(userDtoU, user);
        save(user);
        roleService.delUserRoleByUserid(user.getId());
        for(Long roleId : userDtoU.getRoleIds()){
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            roleService.saveUserRole(userRole);
        }
        return user;
    }

    @Override
    public User save(User user) throws DataAccessException {
        user.setUpdatetime(new Date());
        return userRepository.save(user);
    }

    @Override
    public Page<User> findUserPage(UserDtoS userDtoS, Pageable pageable) {
        Page<User> result = userRepository.findAll(UserSpecs.setQuery(userDtoS), pageable);
        return result;
    }


    @Override
    public LoginLog save(LoginLog loginLog) {
        return loginLogRepository.save(loginLog);
    }

    @Override
    public Page<User> findDelByRoleidPage(Long roleid, Pageable pageable) {
        return userRepository.findDelByRoleIdOrderByUpdateTimeDesc(roleid, pageable);
    }

    @Override
    public Page<User> findByRoleidPage(Long roleid, Pageable pageable) {
        return userRepository.findByRoleIdOrderByCreateTime(roleid, pageable);
    }

    @Override
    public Boolean resetPassword(User user) {
        user.setPassword(passwordEncoder.encode(ConstantValue.INIT_PASSWORD));
        save(user);
        return true;
    }

    @Override
    public User findByIdAndStatus(Long userId, Long status) {
        return userRepository.findByIdAndStatus(userId, status);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findByIdAndIdNotNull(userId);
    }

    @Override
    public User findByUserName(String userName) throws DataAccessException {
        return userRepository.findByUsernameAndStatus(userName, ConstantValue.T_USER_STATUS_ZC);
    }
}
