package com.liuxu.demo.service.intf;

import com.liuxu.demo.common.datamodel.LoginUserReq;
import com.liuxu.demo.common.datamodel.LoginUserResp;
import com.liuxu.demo.common.exception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginUserResp login(LoginUserReq loginUserDto, HttpServletRequest request, HttpServletResponse response) throws MyException;

    void loginOut(HttpServletRequest request, HttpServletResponse response);

    LoginUserResp queryLoginUser();


}
