package com.liuxu.demo.controller;

import com.liuxu.demo.constant.CommonDef;
import com.liuxu.demo.datamodel.AddUserReq;
import com.liuxu.demo.datamodel.AddUserResp;
import com.liuxu.demo.datamodel.LoginUserReq;
import com.liuxu.demo.datamodel.LoginUserResp;
import com.liuxu.demo.exception.MyException;
import com.liuxu.demo.intf.LoginService;
import com.liuxu.demo.intf.UserService;
import com.liuxu.demo.result.Result;
import com.liuxu.demo.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping(value = CommonDef.BASE_PATH)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class) //事务回滚
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", produces = { "application/json" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Result addUser(@Validated @RequestBody AddUserReq addUserReq) throws MyException {
        return new Result(ResultCode.SUCCESS, userService.addUser(addUserReq));
    }
}
