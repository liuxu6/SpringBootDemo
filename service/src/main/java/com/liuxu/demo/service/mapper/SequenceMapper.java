package com.liuxu.demo.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SequenceMapper {

    @Select("select nextval(#{sequence,jdbcType=VARCHAR}) from dual")
    Long getSequenceId(@Param("sequence") String userCode);
}
