package com.liuxu.demo.controller;

import com.liuxu.demo.constant.CommonDef;
import com.liuxu.demo.datamodel.LoginUserReq;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.intf.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = CommonDef.BASE_PATH)
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) //事务回滚
public class LoginManagementController {

    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public LoginUserResp login(@Validated @RequestBody LoginUserReq loginUserReq, HttpServletRequest request, HttpServletResponse response){
        return loginServiceImpl.login(loginUserReq, request, response);

    }

    @RequestMapping(value = "/test", produces = { "application/json" }, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String test(){
        return "hello";
    }

}
