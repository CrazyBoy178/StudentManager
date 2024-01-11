package com.boot.StudentManager.controller;

import com.boot.StudentManager.bean.College;
import com.boot.StudentManager.bean.Record;
import com.boot.StudentManager.bean.Subject;
import com.boot.StudentManager.mapper.CollegeMapper;
import com.boot.StudentManager.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/college")
public class CollegeController extends HttpController<CollegeMapper, College> {
    //localhost:8080/category   查询
    //localhost:8080/category/数字    删除


}
