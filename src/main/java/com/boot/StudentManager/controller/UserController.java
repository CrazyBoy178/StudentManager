package com.boot.StudentManager.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.StudentManager.bean.*;
import com.boot.StudentManager.mapper.StatusMapper;
import com.boot.StudentManager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//去掉跨域限制
@CrossOrigin//解决跨域
@RestController //1.让这个类成为控制器,和前端交互2.把数据以json串形式传给前端
@RequestMapping("/user")
public class UserController extends HttpController<UserMapper, User> {
    @Autowired //相当于new对象
    UserMapper userMapper;//在创建userMapper对象之前,需要添加注解

    @Autowired
    StatusMapper statusMapper;
    //localhost:8080/user           查询
    //localhost:8080/user/数字        删除
    //localhost:8080/user post请求 添加

    @RequestMapping("/check-phone")
    public Map<String, Object> checkPhone(@RequestParam String username){
        System.out.println(username);
        Map<String, Object> map= new HashMap<String, Object>();
        if(userMapper.isExist(username)!=0){
            map.put("code", 0);
            return map;
        }else{
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/sid")
    public Record sid() {
        Record record = new Record();
        for (Status status : statusMapper.selectList(null)) {
            record.add("status", status.status,status.status);
        }

        return record;
    }


    @RequestMapping("/login")//get post
    public Map<String, Object> login(User user) {
        System.out.println(user.username);
        System.out.println(user.password);
        User ret = userMapper.getUser(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (ret == null) {
            map.put("code", 0);//失败
        } else {
            map.put("code", 1);
            map.put("uid", ret.id);//登陆账号的id返回给前端
            map.put("user", ret.username);
            map.put("name", ret.name);
            map.put("status", ret.status);
        }
        return map;
    }


    @PostMapping("/register")//post
    public Map<String, Object> register(@RequestBody RegistrationDTO registrationDTO) {
        Map<String, Object> map;
        String username = registrationDTO.getRe_username();
        String password = registrationDTO.getRe_password(); // 应该加密处理
        String name = registrationDTO.getRe_name();
        if(userMapper.isExist(username)!=0 ||username==null  ||password == null ||name == null){
            map = new HashMap<String, Object>();
            map.put("code", 0);
            return map;
        }else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password); // 请确保密码经过加密处理
            user.setName(name);
            user.setStatus("学生"); // 或其他默认状态
            System.out.println(user.username);
            System.out.println(user.password);
            System.out.println(user.name);
            System.out.println(user.status);
            userMapper.insert(user);
            map = new HashMap<String, Object>();
            map.put("code", 1);
            User newUser = userMapper.getUser(user);
            map.put("uid", newUser.id);//登陆账号的id返回给前端
            map.put("user", newUser.username);
            map.put("name", newUser.name);
            map.put("status", newUser.status);
        }
        return map;
    }
}

