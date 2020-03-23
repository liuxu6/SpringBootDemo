package com.liuxu.demo.mapper;

import com.liuxu.demo.datamodel.AddUserResp;
import com.liuxu.demo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void addUser(UserDTO userDTO);

}
