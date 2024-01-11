package com.boot.StudentManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.College;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollegeMapper extends BaseMapper<College> {

}
