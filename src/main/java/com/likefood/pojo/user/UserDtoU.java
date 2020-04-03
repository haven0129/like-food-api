package com.likefood.pojo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

// 用来更新 User 的表单信息
public class UserDtoU {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    private String headpord;
    @NotEmpty
    private List<Long> roleIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadpord() {
        return headpord;
    }

    public void setHeadpord(String headpord) {
        this.headpord = headpord;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
