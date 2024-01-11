package com.boot.StudentManager.controller;


import com.boot.StudentManager.bean.College;
import com.boot.StudentManager.bean.Record;
import com.boot.StudentManager.bean.Subject;
import com.boot.StudentManager.bean.User;
import com.boot.StudentManager.mapper.CollegeMapper;
import com.boot.StudentManager.mapper.SubjectMapper;
import com.boot.StudentManager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/subject")
public class SubjectController extends HttpController<SubjectMapper, Subject> {
    //localhost:8080/category   查询
    //localhost:8080/category/数字    删除

    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    CollegeMapper collegeMapper;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/cid")
    public Record sid() {
        Record record = new Record();

        for (College college : collegeMapper.selectList(null)) {
            record.add("college", college.collegename,college.collegename);
        }

        return record;
    }


    @RequestMapping("/tid")
    public Record tid() {
        Record record = new Record();

        for (User user : userMapper.selectTeacher()) {
            record.add("teacher", user.name,user.name);
        }

        return record;
    }

}
