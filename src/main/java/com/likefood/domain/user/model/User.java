package com.likefood.domain.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_USER")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "name")
  private String name;
  @Column(name = "createtime")
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date createtime;
  @Column(name = "updatetime")
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  private Date updatetime;
  @Column(name = "lastlogintime")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastlogintime;
  @Column(name = "mobile")
  private String mobile;
  @Column(name = "status")
  private Long status;
  @Column(name = "openid")
  private String openid;
  @Column(name = "headport")
  private String headport;
  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
  @JoinTable(
          name="t_user_role",
          joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
          inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id", unique=true))
  private List<Role> roleList;

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
    this.updatetime = updatetime;
  }

  public Date getLastlogintime() {
    return lastlogintime;
  }

  public void setLastlogintime(Date lastlogintime) {
    this.lastlogintime = lastlogintime;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getHeadport() {
    return headport;
  }

  public void setHeadport(String headport) {
    this.headport = headport;
  }
}
