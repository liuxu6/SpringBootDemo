package com.liuxu.demo.datamodel;

import java.io.Serializable;

public class LoginUserReq implements Serializable{

    private static final long serialVersionUID = -2399225033888216696L;

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
