package com.likefood.pojo.login;


import com.likefood.pojo.user.UserDto;

public class LoginResponse {
    private String token;
    private String message = "此 token 应当以 x-auth-token 属性附在请求的 Header 中";
    private UserDto userInfo;

    public UserDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDto userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
