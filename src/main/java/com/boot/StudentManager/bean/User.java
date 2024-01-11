package com.boot.StudentManager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.boot.StudentManager.util.NotNull;
import lombok.Data;


//注解 @xxx
//bean中的类/对象 跟数据表对应
//说白了 表中有什么,这个类就有什么
@Data //此时这个注解加上了set get那一堆方法
@TableName("tbl_user")//数据库表名
public class User {
    @TableId(type = IdType.AUTO)
    public Integer id;
    @NotNull
    public String username;
    @NotNull
    public String password;
    @NotNull
    public String name;
    @NotNull
    public String status;

}
