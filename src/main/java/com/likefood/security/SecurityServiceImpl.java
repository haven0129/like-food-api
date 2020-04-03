package com.likefood.security;

import com.likefood.domain.user.model.Permission;
import com.likefood.domain.user.service.RoleService;
import com.likefood.pojo.common.MsgValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Autowired
    RoleService roleService;
    @Override
    public boolean authorized(Long userId, Object targetDomainObject, Object permission) {
        List<Permission> permissions = roleService.getUserPermissions(userId);
        for (Permission p:permissions) {
            if (p.getDomain().equals(targetDomainObject.toString()) && p.getPermission().equals(permission.toString())) {
                return true;
            }
        }
        logger.error("authorized: " + MsgValue.NO_PERMISSION + "-" + userId + "-" + targetDomainObject.toString() + " " + permission.toString());
        return false;
    }
}
