package com.liuxu.demo.controller;

import com.liuxu.demo.constant.UrlConstants;
import com.liuxu.demo.datamodel.LoginUserReq;
import org.springframework.http.HttpStatus;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConstants.BASE_PATH)
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) //事务回滚
public class LoginManagementController {


    @RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public void login(@Validated @RequestBody LoginUserReq loginUserReq){
        System.out.println(loginUserReq.getUserName());
        System.out.println(loginUserReq.getPassword());

    }


}
