package com.boot.StudentManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GradeMapper extends BaseMapper<Grade> {


    @Select("select subname,sid from v_tgrade_info where tid=#{tid} order by id desc")
    List<Grade> selectGrade(@Param("tid") int tid);



    @Select("select * from v_tgrade_info where tid=#{tid} and subname=#{subname} order by id desc")
    List<Grade> selectTGrade(@Param("tid") int tid,@Param("subname") String subname);

    @Select("select * from v_tgrade_info where tid=#{tid} and sid=#{sid} order by id desc")
    List<Grade> selectTGradeWithId(@Param("tid") int tid,@Param("sid") int sid);

    @Select("select * from v_tgrade_info where tid=#{tid}  order by id desc")
    List<Grade> selectTGradeById(@Param("tid") int tid);

    @Update("UPDATE tbl_grade SET grade=#{grade} WHERE uid =#{uid} and sid =#{sid}")
    int update(@Param("grade") float grade, @Param("uid")int uid, @Param("sid")int sid);

}
