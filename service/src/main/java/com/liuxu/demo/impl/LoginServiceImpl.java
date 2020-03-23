package com.liuxu.demo.impl;

import com.liuxu.demo.constant.CommonDef;
import com.liuxu.demo.constant.TableNameDef;
import com.liuxu.demo.datamodel.LoginUserReq;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.dto.UserDTO;
import com.liuxu.demo.exception.MyException;
import com.liuxu.demo.exception.MyExceptionHandler;
import com.liuxu.demo.intf.LoginService;
import com.liuxu.demo.mapper.LoginMapper;
import com.liuxu.demo.result.ResultCode;
import com.liuxu.demo.unit.CheckHelper;
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
