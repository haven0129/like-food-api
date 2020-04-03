package com.likefood.domain.user.service;

import com.likefood.domain.user.model.LoginLog;
import com.likefood.domain.user.model.User;
import com.likefood.pojo.user.UserDtoC;
import com.likefood.pojo.user.UserDtoS;
import com.likefood.pojo.user.UserDtoU;
import com.likefood.pojo.user.UserDtoV;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user) throws DataAccessException;
    User updateUser(User user, UserDtoU userDtoU) throws DataAccessException;
    User saveUserFirst(UserDtoC userDtoC) throws DataAccessException;
    User findByUserName(String userName) throws DataAccessException;
    User findById(Long userId);
    User findByIdAndStatus(Long userId, Long status);
    Boolean resetPassword(User user);
    Page<User> findByRoleidPage(Long roleid, Pageable pageable);
    Page<User> findDelByRoleidPage(Long roleid, Pageable pageable);

    LoginLog save(LoginLog loginLog);
    Page<User> findUserPage(UserDtoS userDtoS, Pageable pageable);

}
