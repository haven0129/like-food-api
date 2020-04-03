package com.likefood.domain.user.service;


import com.likefood.domain.user.model.Permission;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.UserRole;

import java.util.List;

public interface RoleService {
    List<Role> getRoles(Long userId);

    List<Role> getRoleList();

    List<Permission> getUserPermissions(Long userId);

    UserRole saveUserRole(UserRole userRole);

    List<UserRole> getUserRoleList(Long userId);

    Long delUserRole(UserRole userRole);

    Role getRoleById(Long roleId);

    void delUserRoleByUserid(Long userid);
}
