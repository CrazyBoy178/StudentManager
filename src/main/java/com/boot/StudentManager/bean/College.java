package com.boot.StudentManager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.StudentManager.util.NotNull;
import lombok.Data;

@Data
@TableName("tbl_college")
public class College {


    @TableId(type = IdType.AUTO)
    public Integer id;

    @NotNull
    public String collegename;


}
