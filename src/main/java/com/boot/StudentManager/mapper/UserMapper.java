package com.boot.StudentManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.Choose;
import com.boot.StudentManager.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //@Autowired
public interface UserMapper extends BaseMapper<User> {
    //传登陆信息 查数据表tbl_user表查完之后
    //传user username password
    //@Select查询
    //@Insert插入
    //@Update修改
    //@Delete删除

    //有了BaseMapper不用写sql语句了
    //BaseMapper
    //selectById(id数字)     查询一条数据
    //selectList(null)      查询表中所有数据
    //insert(对象)           向表中插入数据
    //UpdateById(id数字)     按照id修改一条数据
    //deleteById(id数字)     按照id删除一条数据
    @Select("select * from tbl_user where username=#{username} and password=#{password}")
    User getUser(User user);//抽象方法 sql语句跟抽象方法是对应在一起的
    //如果调用getUser()方法,等同执行sql语句

    @Select("select * from tbl_user where status='老师' order by id desc")
    List<User> selectTeacher();

    @Select("select count(*) from tbl_user where username=#{username}")
    int isExist(String username);

    @Insert("insert into tbl_user(username, password, name, status) values (#{username}, #{password}, #{name}, '学生')")
    int insertUser(User user);

}


