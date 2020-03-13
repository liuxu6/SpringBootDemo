package com.liuxu.demo.impl;

import com.liuxu.demo.constant.CommonDef;
import com.liuxu.demo.datamodel.LoginUserReq;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.intf.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public LoginUserResp login(LoginUserReq loginUserReq, HttpServletRequest request, HttpServletResponse response){
        // 校验是否为空

        LoginUserResp userResp = new LoginUserResp();
        userResp.setUserName(loginUserReq.getUserName());

        // 设置session和日志信息
        setUserSessionAndLog(request, userResp);

        return userResp;
    }


    private void setUserSessionAndLog(HttpServletRequest request, LoginUserResp loginUserDto){
        // 缓存信息
        if (loginUserDto != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(CommonDef.USER_LOGIN_INFO, loginUserDto);
        }
    }
}
