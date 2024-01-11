package com.boot.StudentManager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.StudentManager.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_subject")
public class Subject {


    @TableId(type = IdType.AUTO)
    public Integer id;

    @NotNull
    public String subname;
    @NotNull
    public float credit;
    @NotNull
    public String college;

    public String teacher;

}
