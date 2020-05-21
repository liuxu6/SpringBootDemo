package com.liuxu.demo.service.mapper;

import com.liuxu.demo.common.dto.UserDTO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    UserDTO selectLoginUserByCode(@Param("userCode") String userCode);

}
