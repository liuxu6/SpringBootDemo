package com.liuxu.web.controller;

import com.liuxu.common.constant.CommonDef;
import com.liuxu.common.datamodel.AddUserReq;
import com.liuxu.common.exception.MyException;
import com.liuxu.service.intf.UserService;
import com.liuxu.common.result.Result;
import com.liuxu.common.result.ResultCode;
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
