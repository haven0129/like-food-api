package com.likefood.domain.user.model;

import javax.persistence.*;

@Entity
@Table(name = "T_ROLE_PERMISSION")
public class RolePermission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "role_id")
  private Long roleId;
  @Column(name = "permission_id")
  private Long permissionId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
  }
}
