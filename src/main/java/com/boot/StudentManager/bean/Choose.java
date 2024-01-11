package com.boot.StudentManager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_choose")
public class Choose {
    @TableId(type = IdType.AUTO)
    public Integer id;

    public Integer uid;

    public Integer sid;

    @TableField(exist = false)
    public String subname;

    @TableField(exist = false)
    public float credit;

    @TableField(exist = false)
    public String college;

    @TableField(exist = false)
    public String name;

    @TableField(exist = false)
    public String username;
}
