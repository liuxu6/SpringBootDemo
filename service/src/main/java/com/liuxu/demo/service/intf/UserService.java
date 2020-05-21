package com.liuxu.demo.service.intf;

import com.liuxu.demo.common.datamodel.AddUserReq;
import com.liuxu.demo.common.datamodel.AddUserResp;
import com.liuxu.demo.common.exception.MyException;


public interface UserService {

    AddUserResp addUser(AddUserReq addUserReq) throws MyException;

}
