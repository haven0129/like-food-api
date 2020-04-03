package com.likefood.pojoconverter;

import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.User;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.user.UserDto;
import com.likefood.pojo.user.UserDtoC;
import com.likefood.pojo.user.UserDtoU;
import com.likefood.pojo.user.UserDtoV;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserDtoConverterImpl implements UserDtoConverter {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto buildUserDto(User source) {
        UserDto result = new UserDto();
        BeanUtils.copyProperties(source, result);
        return result;
    }

    @Override
    public User buildUser(UserDtoU source, User result) {
        result.setName(source.getName());
        result.setHeadport(source.getHeadpord());
        return result;
    }

    @Override
    public UserDtoV buildUserDtoV(User source, List<Role> roleList) {
        UserDtoV result = new UserDtoV();
        BeanUtils.copyProperties(source, result);
        result.setRoleList(roleList);
        return result;
    }

    @Override
    public User buildUser(UserDtoC source) {
        User result = new User();
        BeanUtils.copyProperties(source, result);
        result.setPassword(passwordEncoder.encode(ConstantValue.INIT_PASSWORD));
        result.setMobile(source.getUsername());
        Date date = new Date();
        result.setCreatetime(date);
        result.setUpdatetime(date);
        result.setStatus(ConstantValue.T_USER_STATUS_INIT);
        return result;
    }

}