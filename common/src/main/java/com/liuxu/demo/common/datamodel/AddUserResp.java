package com.liuxu.demo.common.datamodel;

import java.io.Serializable;

public class AddUserResp implements Serializable {

    private static final long serialVersionUID = -8832438356668756105L;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
