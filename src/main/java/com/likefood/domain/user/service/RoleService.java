package com.likefood.domain.user.service;


import com.likefood.domain.user.model.Permission;
import com.likefood.domain.user.model.Role;
import com.likefood.domain.user.model.UserRole;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户ID查询角色列表
     * @param userId
     * @return
     */
    List<Role> getRoles(Long userId);

    /**
     * 查询角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 根据用户ID查询用户权限列表
     * @param userId
     * @return
     */
    List<Permission> getUserPermissions(Long userId);

    /**
     * 保存用户角色关联关系
     * @param userRole
     * @return
     */
    UserRole saveUserRole(UserRole userRole);

    /**
     * 根据用户ID查询用户角色关联
     * @param userId
     * @return
     */
    List<UserRole> getUserRoleList(Long userId);

    /**
     * 删除用户角色关联
     * @param userRole
     * @return
     */
    Long delUserRole(UserRole userRole);

    /**
     * 根据角色ID查询角色信息
     * @param roleId
     * @return
     */
    Role getRoleById(Long roleId);

    /**
     * 根据用户ID删除当前用户所有角色关联
     * @param userid
     */
    void delUserRoleByUserid(Long userid);
}
