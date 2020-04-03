package com.likefood.security;

import com.likefood.domain.user.model.User;
import com.likefood.domain.user.service.UserService;
import com.likefood.pojo.common.MsgValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    private static final Logger logger = LoggerFactory.getLogger(MyPermissionEvaluator.class);
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        if (user == null) {
            logger.error("hasPermission : "+ MsgValue.NO_USER_EXIT + username);
            throw  new UsernameNotFoundException(MsgValue.NO_USER_EXIT );
        }
        return securityService.authorized(user.getId(), targetDomainObject.toString(), permission.toString());
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
    }
}