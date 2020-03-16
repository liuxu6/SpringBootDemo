package com.liuxu.demo.interceptor;

import com.liuxu.demo.constant.CommonDef;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.exception.DemoException;
import com.liuxu.demo.result.ResultCode;
import com.liuxu.demo.unit.ThreadLocalMap;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws UnsupportedEncodingException, DemoException {

        LoginUserResp loginUserDto = (LoginUserResp) request.getSession().getAttribute(CommonDef.USER_LOGIN_INFO);

        if (loginUserDto == null) {
//            DemoExceptionHandler.publish("401", "请登录");
            throw new DemoException(ResultCode.USER_NOT_LOGIN);
        }
//        setupDefault(request);

        ThreadLocalMap.setUp(new HashMap<String, Object>());

        if (loginUserDto.getUserName() != null) {
            ThreadLocalMap.set("USER_NAME", loginUserDto.getUserName());
        }
        return true;
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
