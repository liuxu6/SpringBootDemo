package com.liuxu.service.impl;

import com.liuxu.common.constant.CommonDef;
import com.liuxu.common.constant.TableNameDef;
import com.liuxu.common.datamodel.LoginUserReq;
import com.liuxu.common.datamodel.LoginUserResp;
import com.liuxu.common.dto.UserDTO;
import com.liuxu.common.exception.MyException;
import com.liuxu.common.exception.MyExceptionHandler;
import com.liuxu.service.intf.LoginService;
import com.liuxu.service.mapper.LoginMapper;
import com.liuxu.common.result.ResultCode;
import com.liuxu.common.util.CheckHelper;
import com.liuxu.common.util.ThreadLocalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public LoginUserResp login(LoginUserReq loginUserReq, HttpServletRequest request, HttpServletResponse response) throws MyException {
        // 校验是否为空
        CheckHelper.checkIsNotEmpty(TableNameDef.USER_TABLE_USER_CODE, loginUserReq.getUserCode());
        CheckHelper.checkIsNotEmpty(TableNameDef.USER_TABLE_PASSWORD, loginUserReq.getPassword());

        LoginUserResp userResp = new LoginUserResp();

        UserDTO userDTO = loginMapper.selectLoginUserByCode(loginUserReq.getUserCode());

        if (userDTO == null){
            MyExceptionHandler.publish(ResultCode.PASSWORD_IS_ERROR);
        }else {
            String password = null;
            try {
                password = md5(loginUserReq.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (!userDTO.getPassword().equals(password)) {
                MyExceptionHandler.publish(ResultCode.PASSWORD_IS_ERROR);

            }else {
                //登录成功
                userResp.setUserId(userDTO.getUserId());
                userResp.setUserName(userDTO.getUserName());
                userResp.setUserCode(userDTO.getUserCode());

                // 设置session和日志信息
                setUserSessionAndLog(request, userResp);
            }
        }

        return userResp;
    }

    @Override
    public void loginOut(HttpServletRequest request, HttpServletResponse response){
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute(CommonDef.USER_LOGIN_INFO);
        httpSession.invalidate();
    }

    @Override
    public LoginUserResp queryLoginUser(){
        LoginUserResp userResp = new LoginUserResp();
        userResp.setUserId(ThreadLocalMap.getLong(CommonDef.USER_LOGIN_INFO_USER_ID));
        userResp.setUserCode(ThreadLocalMap.getString(CommonDef.USER_LOGIN_INFO_USER_CODE));
        userResp.setUserName(ThreadLocalMap.getString(CommonDef.USER_LOGIN_INFO_USER_NAME));
        return userResp;
    }

    private void setUserSessionAndLog(HttpServletRequest request, LoginUserResp loginUserDto){
        // 缓存信息
        if (loginUserDto != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(CommonDef.USER_LOGIN_INFO, loginUserDto);
        }
    }

    private String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }
}
