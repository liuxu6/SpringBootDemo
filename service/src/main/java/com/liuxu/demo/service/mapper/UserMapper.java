package com.liuxu.demo.service.mapper;

import com.liuxu.demo.common.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void addUser(UserDTO userDTO);

}
