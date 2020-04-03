package com.likefood.pojo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdatePasswordByCodeDto {
    @NotBlank
    @Pattern(regexp="(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$", message = "手机号格式不正确")
    private String mobile;

    @NotBlank
    @Pattern(regexp="(^\\d{6}$)", message = "验证码格式不正确")
    private String code;
    @NotBlank
    @Size(min=6, max=32, message = "密码长度为6到32个字符之间")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public String getCode() {
        return code;
    }

    public String getMobile() {
        return mobile;
    }


}
