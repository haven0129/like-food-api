package com.likefood.security;

import com.likefood.domain.user.model.User;
import com.likefood.domain.user.service.RoleService;
import com.likefood.domain.user.service.UserService;
import com.likefood.pojo.common.ErrorMsg;
import com.likefood.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (username.isEmpty()) {
            logger.error(ErrorMsg.LOGIN_ERROR + ErrorMsg.USERNAME_NULL + ":" + username);
            throw new UsernameNotFoundException(ErrorMsg.USERNAME_NULL);
        }

        User user = userService.findByUserName(username);
        if (user == null) {
            logger.error(ErrorMsg.LOGIN_ERROR + ErrorMsg.USERNAME_NOT_EXIT + ":" + username);
            throw new UsernameNotFoundException(ErrorMsg.USERNAME_NOT_EXIT);
        }else{  // 不为空
            if(StringUtils.isEmptyOrNull(user.getPassword())){      // 如果密码为空
                logger.error(ErrorMsg.LOGIN_ERROR + ErrorMsg.NO_PASSWORD + ":" + username);
                throw new UsernameNotFoundException(ErrorMsg.NO_PASSWORD);
            }
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        roleService.getRoles(user.getId()).forEach(r ->
        {
            if (r != null) { authorities.add(new SimpleGrantedAuthority(r.getRoleName())); }
        });

        return new org.springframework.security.core.userdetails.User(
                username, user.getPassword(),
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true
                authorities);
    }
}