//package com.liuxu.demo.test;
//
//
//import com.liuxu.demo.common.dto.UserDTO;
//import com.liuxu.demo.web.SpringBootDemoApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringBootDemoApplication.class)
//public class MyTest {
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    @Test
//    public void test01(){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("liuxu");
//        rabbitTemplate.convertAndSend("demo.email","email",userDTO);
//    }
//
//
//    @Test
//    public void test02(){
//        Object o = rabbitTemplate.receiveAndConvert("demo.email");
//        assert o != null;
//        System.out.println(o.getClass());
//        System.out.println(o);
//
//    }
//}
