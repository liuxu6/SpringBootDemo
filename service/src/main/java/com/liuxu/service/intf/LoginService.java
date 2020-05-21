package com.liuxu.service.intf;

import com.liuxu.common.datamodel.LoginUserReq;
import com.liuxu.common.datamodel.LoginUserResp;
import com.liuxu.common.exception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginUserResp login(LoginUserReq loginUserDto, HttpServletRequest request, HttpServletResponse response) throws MyException;

    void loginOut(HttpServletRequest request, HttpServletResponse response);

    LoginUserResp queryLoginUser();


}
