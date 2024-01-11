package com.boot.StudentManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.Status;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatusMapper extends BaseMapper<Status> {
}
