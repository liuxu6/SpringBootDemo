package com.liuxu.demo.datamodel;

import java.io.Serializable;

public class LoginUserResp implements Serializable {

    private static final long serialVersionUID = 7888739049830215592L;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
