package com.liuxu.demo.common.datamodel;

import java.io.Serializable;

public class LoginUserResp implements Serializable {

    private static final long serialVersionUID = 7888739049830215592L;

    private Long userId;

    private String userCode;

    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

}
