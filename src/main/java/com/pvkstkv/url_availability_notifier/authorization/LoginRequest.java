package com.pvkstkv.url_availability_notifier.authorization;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String login;
    private String password;


    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
