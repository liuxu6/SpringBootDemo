package com.liuxu.common.datamodel;

import java.io.Serializable;

public class AddUserReq implements Serializable {

    private static final long serialVersionUID = 4836075321669766427L;

    private String userCode;

    private String userName;

    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

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
