package com.liuxu.demo.web.controller;

import com.liuxu.demo.common.constant.CommonDef;
import com.liuxu.demo.common.datamodel.LoginUserReq;
import com.liuxu.demo.common.exception.MyException;
import com.liuxu.demo.service.intf.LoginService;
import com.liuxu.demo.common.result.Result;
import com.liuxu.demo.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = CommonDef.BASE_PATH)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Result login(@Validated @RequestBody LoginUserReq loginUserReq, HttpServletRequest request, HttpServletResponse response) throws MyException {
        return new Result(ResultCode.SUCCESS,loginService.login(loginUserReq, request, response));

    }

    @RequestMapping(value = "/loginout", produces = { "application/json" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public void loginOut(HttpServletRequest request, HttpServletResponse response){
        loginService.loginOut(request, response);
    }

    @RequestMapping(value = "/loginuser", produces = { "application/json" }, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Result queryLoginUser() throws MyException {
        return new Result(ResultCode.SUCCESS, loginService.queryLoginUser());
    }

    @RequestMapping(value = "/test", produces = { "application/json" }, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String test(){
        return "hello";
    }

}
