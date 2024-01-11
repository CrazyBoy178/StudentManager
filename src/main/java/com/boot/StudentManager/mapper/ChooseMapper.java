package com.boot.StudentManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.Choose;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChooseMapper extends BaseMapper<Choose> {

    @Select("select * from v_subject_info where uid=#{uid} order by id desc")
    List<Choose> selectChoose(@Param("uid") int uid);

    @Select("select * from v_choose_info order by id desc")
    List<Choose> selectChooseInfo();



}
