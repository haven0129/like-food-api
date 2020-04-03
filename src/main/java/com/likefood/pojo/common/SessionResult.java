package com.likefood.pojo.common;

public class SessionResult extends Result {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SessionResult(Boolean success, String message, String token) {
        super(success, message);
        this.token = token;
    }
}
