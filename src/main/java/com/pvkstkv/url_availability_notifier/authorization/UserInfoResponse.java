package com.pvkstkv.url_availability_notifier.authorization;

import java.util.List;

public class UserInfoResponse {
    public Long id;
    public String login;
    public List<String> roles;

    public UserInfoResponse(Long id, String login, List<String> roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
