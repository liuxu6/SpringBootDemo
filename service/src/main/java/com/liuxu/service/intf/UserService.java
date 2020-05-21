package com.liuxu.service.intf;

import com.liuxu.common.datamodel.AddUserReq;
import com.liuxu.common.datamodel.AddUserResp;
import com.liuxu.common.exception.MyException;


public interface UserService {

    AddUserResp addUser(AddUserReq addUserReq) throws MyException;

}
