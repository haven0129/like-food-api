package com.likefood.domain.user.model;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "T_LOGIN_LOG")
public class LoginLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="userid")
  private Long userid;
  @Column(name="username")
  private String username;
  @Column(name="name")
  private String name;
  @Column(name="logintype")
  private String logintype;
  @Column(name="loginaddr")
  private String loginaddr;
  @Column(name="logintime")
  private Date logintime;

  public String getLoginaddr() {
    return loginaddr;
  }

  public void setLoginaddr(String loginaddr) {
    this.loginaddr = loginaddr;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getLogintype() {
    return logintype;
  }

  public void setLogintype(String logintype) {
    this.logintype = logintype;
  }


  public Date getLogintime() {
    return logintime;
  }

  public void setLogintime(Date logintime) {
    this.logintime = logintime;
  }
}
