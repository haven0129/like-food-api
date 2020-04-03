package com.likefood.pojo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

// 用来新建 User 的表单信息
public class UserDtoC {
    @NotBlank
    @Pattern(regexp="(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$", message = "手机号格式不正确")
    private String username;
    @NotBlank
    private String name;
    private String headpord;
    @NotEmpty
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
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
