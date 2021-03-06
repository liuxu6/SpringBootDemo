package com.liuxu.demo.service.interceptor;

import com.liuxu.demo.common.constant.CommonDef;
import com.liuxu.demo.common.datamodel.LoginUserResp;
import com.liuxu.demo.common.exception.MyException;
import com.liuxu.demo.common.exception.MyExceptionHandler;
import com.liuxu.demo.common.result.ResultCode;
import com.liuxu.demo.common.util.ThreadLocalMap;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Service
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws MyException {

        //spring-session
        LoginUserResp loginUserDto = (LoginUserResp) request.getSession().getAttribute(CommonDef.USER_LOGIN_INFO);

        if (loginUserDto == null) {
            MyExceptionHandler.publish(ResultCode.USER_NOT_LOGIN);
        }else {
//            setupDefault(request);
            if (loginUserDto.getUserName() != null) {
                ThreadLocalMap.setUp(new HashMap<>());
                ThreadLocalMap.set(CommonDef.USER_LOGIN_INFO_USER_ID, loginUserDto.getUserId());
                ThreadLocalMap.set(CommonDef.USER_LOGIN_INFO_USER_CODE, loginUserDto.getUserCode());
                ThreadLocalMap.set(CommonDef.USER_LOGIN_INFO_USER_NAME, loginUserDto.getUserName());
                return true;
            }
        }
        return false;
    }

//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
//        long endTime = System.currentTimeMillis();
//        long startTime = (Long) request.getAttribute("startTime");
//
//        LOGGER.info("outer LocalInteceptor:" + request.getRequestURL() + " --> cost time:" + (endTime - startTime));
//    }
//
//    protected void setupDefault(HttpServletRequest request) throws UnsupportedEncodingException {
//        long startTime = System.currentTimeMillis();
//        request.setAttribute("startTime", startTime);
//        request.setCharacterEncoding("UTF-8");
//
//        Locale locale = Locale.ENGLISH;
//        LocaleContextHolder.setLocale(locale);
//    }
}
