package com.likefood.pojo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SetPasswordDto {
    @NotNull
    @Size(min=6, max=32, message = "密码长度为6到32个字符之间")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
