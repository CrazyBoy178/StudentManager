package com.boot.StudentManager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.StudentManager.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_grade")
public class Grade {
    @TableId(type = IdType.AUTO)
    public Integer id;

    @NotNull
    public Integer uid;

    @NotNull
    public Integer sid;

    @NotNull
    public String grade;

    @TableField(exist = false)
    public String subname;

    @TableField(exist = false)
    public float credit;

    @TableField(exist = false)
    public String college;

    @TableField(exist = false)
    public String teacher;

    @TableField(exist = false)
    public String name;

    @TableField(exist = false)
    public Integer tid;

}
