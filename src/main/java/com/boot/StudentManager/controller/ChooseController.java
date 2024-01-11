package com.boot.StudentManager.controller;

import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.boot.StudentManager.bean.Choose;
import com.boot.StudentManager.mapper.ChooseMapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/choose")
public class ChooseController extends HttpController<ChooseMapper, Choose>{

    @Autowired
    ChooseMapper chooseMapper;

    @RequestMapping("/choose")
    public List<Choose> choose(int uid) {
        return chooseMapper.selectChoose(uid);
    }

    @RequestMapping("/chooseinfo")
    public List<Choose> chooseinfo() {
        return chooseMapper.selectChooseInfo();
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("没有选择文件");
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String currentTime = dateFormat.format(new Date());
            // 指定上传文件保存的目录，例如：/tmp/upload/
            String uploadDir = "D:/create/file/";
            File dir = new File(uploadDir);

            // 如果目录不存在，创建它
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 获取上传文件的原始文件名
            String originalFileName = currentTime+file.getOriginalFilename();
            System.out.println(file.getOriginalFilename());

            String filePath = uploadDir+originalFileName;
            // 将上传文件保存到本地
            file.transferTo(new File(filePath));
            System.out.println(filePath);

            try (Workbook workbook = WorkbookFactory.create((Files.newInputStream(Paths.get(filePath))))) {
                Sheet sheet = workbook.getSheetAt(0); // 假设 Excel 文件只有一个表格


                for (Row row : sheet) {
                    // 跳过标题行
                    if (row.getRowNum() == 0 && row.getPhysicalNumberOfCells() > 0) {
                        continue; // 跳过标题行
                    }

                    Choose choose = new Choose();
                    choose.setUid((int) row.getCell(0).getNumericCellValue()); // 假设第一列为 uid
                    choose.setSid((int) row.getCell(1).getNumericCellValue()); // 假设第二列为 sid

                    // 插入数据到数据库
                    chooseMapper.insert(choose);
                }

                return ResponseEntity.ok("上传成功,请返回刷新");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.ok("数据库有重复数据,请返回刷新");
//
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("上传失败,请返回刷新");

        }

    }
}
