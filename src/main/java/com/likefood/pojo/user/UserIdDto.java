package com.likefood.pojo.user;

import javax.validation.constraints.NotNull;

// 用来返回用户信息
public class UserIdDto {
    @NotNull
    private Long userid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
