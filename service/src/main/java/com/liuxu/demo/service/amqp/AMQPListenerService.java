package com.liuxu.demo.service.amqp;

import com.liuxu.demo.common.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AMQPListenerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @RabbitListener(queues = "demo.email")
//    public void receiver(UserDTO userDTO){
//        logger.info("用户已存在，用户信息为:"+userDTO.toString());
//    }


}
