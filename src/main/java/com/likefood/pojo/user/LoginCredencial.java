package com.likefood.pojo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginCredencial {
    @NotBlank
    @Pattern(regexp="(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$", message = "手机号格式不正确")
    private String username;
    @NotBlank
    private String password;



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

}
