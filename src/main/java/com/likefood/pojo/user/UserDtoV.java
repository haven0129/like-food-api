package com.likefood.pojo.user;

import com.likefood.domain.user.model.Role;

import java.util.List;

// 用来新建 User 的表单信息
public class UserDtoV {
    private Long id;
    private String username;
    private String name;
    private String headpord;
    private Long sttus;
    private List<Role> roleList;


    public Long getSttus() {
        return sttus;
    }

    public void setSttus(Long sttus) {
        this.sttus = sttus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getHeadpord() {
        return headpord;
    }

    public void setHeadpord(String headpord) {
        this.headpord = headpord;
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
}
