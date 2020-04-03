package com.likefood.pojo.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MobileDto {
    @NotBlank
    @Pattern(regexp="(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$", message = "手机号格式不正确")

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

