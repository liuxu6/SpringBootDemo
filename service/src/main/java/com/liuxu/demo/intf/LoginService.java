package com.liuxu.demo.intf;

import com.liuxu.demo.datamodel.LoginUserReq;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.exception.MyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginUserResp login(LoginUserReq loginUserDto, HttpServletRequest request, HttpServletResponse response) throws MyException;

    void loginOut(HttpServletRequest request, HttpServletResponse response);

}
