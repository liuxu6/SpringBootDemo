package com.liuxu.demo.mapper;

import com.liuxu.demo.dto.UserDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

//    @Select("select a.user_id, a.user_code, a.user_name,a.password,a.state from user a where a.state = 'A' and a.user_code = #{userCode," +
//            "jdbcType=VARCHAR}")
    UserDTO selectLoginUserByCode(@Param("userCode") String userCode);

}
