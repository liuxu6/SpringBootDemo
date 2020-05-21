package com.liuxu.service.impl;

import com.liuxu.common.constant.SequenceDef;
import com.liuxu.common.constant.TableNameDef;
import com.liuxu.common.datamodel.AddUserReq;
import com.liuxu.common.datamodel.AddUserResp;
import com.liuxu.common.dto.UserDTO;
import com.liuxu.common.exception.MyException;
import com.liuxu.common.exception.MyExceptionHandler;
import com.liuxu.service.intf.UserService;
import com.liuxu.service.mapper.LoginMapper;
import com.liuxu.service.mapper.SequenceMapper;
import com.liuxu.service.mapper.UserMapper;
import com.liuxu.common.result.ResultCode;
import com.liuxu.common.util.CheckHelper;
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

    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public AddUserResp addUser(AddUserReq addUserReq) throws MyException {
        AddUserResp addUserResp = new AddUserResp();

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

            long userId = sequenceMapper.getSequenceId(SequenceDef.USER_ID_SEQ);

            userDTO = new UserDTO();
            userDTO.setUserId(userId);
            userDTO.setUserCode(addUserReq.getUserCode());
            userDTO.setUserName(addUserReq.getUserName());
            userDTO.setPassword(password);
            userDTO.setState("A");
            userMapper.addUser(userDTO);

            addUserResp.setUserId(userId);

        }else {
            MyExceptionHandler.publish(ResultCode.DATA_IS_EXISTS,"用户");
        }

        return addUserResp;
    }

    private String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        return new BigInteger(md.digest()).toString(32);
    }
}
