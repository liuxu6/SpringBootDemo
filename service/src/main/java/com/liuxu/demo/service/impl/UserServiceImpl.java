package com.liuxu.demo.service.impl;

import com.liuxu.demo.common.constant.SequenceDef;
import com.liuxu.demo.common.constant.TableNameDef;
import com.liuxu.demo.common.datamodel.AddUserReq;
import com.liuxu.demo.common.datamodel.AddUserResp;
import com.liuxu.demo.common.dto.UserDTO;
import com.liuxu.demo.common.exception.MyException;
import com.liuxu.demo.common.exception.MyExceptionHandler;
import com.liuxu.demo.service.intf.UserService;
import com.liuxu.demo.service.mapper.LoginMapper;
import com.liuxu.demo.service.mapper.UserMapper;
import com.liuxu.demo.service.mapper.SequenceMapper;
import com.liuxu.demo.common.result.ResultCode;
import com.liuxu.demo.common.util.CheckHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
            password = CheckHelper.md5(addUserReq.getPassword());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
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
            MyExceptionHandler.publish(ResultCode.DATA_IS_EXISTS,TableNameDef.USER_TABLE_USER_NAME);
            logger.error("用户已存在，用户信息为："+userDTO.toString());
        }

//        消息队列  模拟发送消息
//        amqpDemo(userDTO);
        return addUserResp;
    }

    private void amqpDemo(UserDTO userDTO){
        rabbitTemplate.convertAndSend("demo.email","email",userDTO);
    }

}
