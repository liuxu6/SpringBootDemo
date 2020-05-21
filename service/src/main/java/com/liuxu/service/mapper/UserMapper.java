package com.liuxu.service.mapper;

import com.liuxu.common.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void addUser(UserDTO userDTO);

}
