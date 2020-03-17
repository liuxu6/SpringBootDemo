package com.liuxu.demo.datamodel;

import java.io.Serializable;

public class LoginUserReq implements Serializable{

    private static final long serialVersionUID = -2399225033888216696L;

    private String userCode;

    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
