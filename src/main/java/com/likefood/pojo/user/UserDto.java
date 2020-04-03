package com.likefood.pojo.user;

// 用来返回用户信息
public class UserDto {
    private Long id;
    private String username;
    private String mobile;
    private String name;
    private String headport;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadport() {
        return headport;
    }

    public void setHeadport(String headport) {
        this.headport = headport;
    }
}
