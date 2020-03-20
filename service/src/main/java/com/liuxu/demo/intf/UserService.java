package com.liuxu.demo.intf;

import com.liuxu.demo.datamodel.AddUserReq;
import com.liuxu.demo.datamodel.AddUserResp;
import com.liuxu.demo.exception.MyException;


public interface UserService {

    AddUserResp addUser(AddUserReq addUserReq) throws MyException;

}
