package com.likefood.pojoconverter;


import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.User;
import com.likefood.pojo.user.UserDto;
import com.likefood.pojo.user.UserDtoC;
import com.likefood.pojo.user.UserDtoU;
import com.likefood.pojo.user.UserDtoV;

import java.util.List;


public interface UserDtoConverter {

    UserDto buildUserDto(User source);

    User buildUser(UserDtoC source);

    UserDtoV buildUserDtoV(User source, List<Role> roleList);

    User buildUser(UserDtoU source, User result);

}
