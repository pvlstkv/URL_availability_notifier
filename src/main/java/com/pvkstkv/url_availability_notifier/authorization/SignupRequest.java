package com.pvkstkv.url_availability_notifier.authorization;

import java.util.HashSet;
import java.util.Set;

public class SignupRequest {
    public String login;
    public String password;

    public Set<String> roles;

    public SignupRequest(String login, String password) {
        this.login = login;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add("user");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
