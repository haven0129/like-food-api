package com.likefood.pojo.user;

import javax.validation.constraints.Size;

public class UpdatePasswordDto {
    @Size(min=6, max=32, message = "密码长度为6到32个字符之间")
    private String oldPassword;

    @Size(min=6, max=32, message = "密码长度为6到32个字符之间")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

}
