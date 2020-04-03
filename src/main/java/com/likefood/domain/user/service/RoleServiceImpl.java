package com.likefood.domain.user.service;

import com.likefood.domain.user.model.Permission;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.UserRole;
import com.likefood.domain.user.repository.PermissionRepository;
import com.likefood.domain.user.repository.RoleRepository;
import com.likefood.domain.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<Role> getRoleList() {
        return roleRepository.findByIdNotNullOrderById();
    }

    @Override
    public List<Role> getRoles(Long userId) {
        return roleRepository.findByUserid(userId);
    }

    @Override
    public List<Permission> getUserPermissions(Long userId) {
        return permissionRepository.findPermissionByUserId(userId);
    }

    @Override
    public void delUserRoleByUserid(Long userid) {
        userRoleRepository.deleteByUserId(userid);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findByIdAndIdNotNull(roleId);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getUserRoleList(Long userId) {
        return null;
    }

    @Override
    public Long delUserRole(UserRole userRole) {
        return null;
    }
}
