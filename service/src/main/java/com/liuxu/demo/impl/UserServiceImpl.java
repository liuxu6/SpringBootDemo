package com.liuxu.demo.impl;

import com.liuxu.demo.constant.TableNameDef;
import com.liuxu.demo.datamodel.AddUserReq;
import com.liuxu.demo.datamodel.AddUserResp;
import com.liuxu.demo.dto.UserDTO;
import com.liuxu.demo.exception.MyException;
import com.liuxu.demo.exception.MyExceptionHandler;
import com.liuxu.demo.intf.UserService;
import com.liuxu.demo.mapper.LoginMapper;
import com.liuxu.demo.mapper.UserMapper;
import com.liuxu.demo.result.ResultCode;
import com.liuxu.demo.unit.CheckHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public AddUserResp addUser(AddUserReq addUserReq) throws MyException {
        AddUserResp addUserResDto = new AddUserResp();

        if (null == addUserReq) {
            return null;
        }

        CheckHelper.checkIsNotEmpty(TableNameDef.USER_TABLE_USER_CODE, addUserReq.getUserCode());
        CheckHelper.checkIsNotEmpty(TableNameDef.USER_TABLE_USER_NAME, addUserReq.getUserName());
        CheckHelper.checkIsNotEmpty(TableNameDef.USER_TABLE_PASSWORD, addUserReq.getPassword());

        String password = null;
        try {
            password = md5(addUserReq.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        UserDTO userDTO = loginMapper.selectLoginUserByCode(addUserReq.getUserCode());
        if (userDTO == null){
            userDTO = new UserDTO();
            userDTO.setUserCode(addUserReq.getUserCode());
            userDTO.setUserName(addUserReq.getUserName());
            userDTO.setPassword(password);
            userDTO.setState("A");

            userMapper.addUser(userDTO);

        }else {
            MyExceptionHandler.publish(ResultCode.DATA_IS_EXISTS,"用户");
        }



        return addUserResDto;
    }

    private String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }
}
