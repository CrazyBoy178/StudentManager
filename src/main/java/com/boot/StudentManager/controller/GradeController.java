package com.boot.StudentManager.controller;

import com.boot.StudentManager.bean.Choose;
import com.boot.StudentManager.bean.Grade;
import com.boot.StudentManager.bean.Record;
import com.boot.StudentManager.mapper.ChooseMapper;
import com.boot.StudentManager.mapper.GradeMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/grade")
public class GradeController extends HttpController<GradeMapper, Grade>{

    @Autowired
    GradeMapper gradeMapper;

    @Autowired
    ChooseMapper chooseMapper;


    @RequestMapping("/grade")
    public List<Grade> grade(int uid) {
        return gradeMapper.selectGrade(uid);
    }

    @RequestMapping("/tgradeById")
    public List<Grade> tgradeById(int tid) {
        return gradeMapper.selectTGradeById(tid);
    }

    @RequestMapping("/tgrade")
    public List<Grade> tgrade(int tid,String subname) {
        return gradeMapper.selectTGrade(tid,subname);
    }

    @RequestMapping("/with_sid")
    public List<Grade> tgrade(int tid,int sid) {
        return gradeMapper.selectTGradeWithId(tid,sid);
    }

    @RequestMapping("/getSubname")
    public Record getSubname(int tid) {
        Record record = new Record();
        for (Grade grade : gradeMapper.selectGrade(tid)) {
            record.add("subname", grade.subname, grade.sid);
        }
        return record;
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
                    Grade grade = new Grade();

                    try{

                        choose.setUid((int) row.getCell(0).getNumericCellValue());
                        grade.setUid((int) row.getCell(0).getNumericCellValue());

                        System.out.println((int) row.getCell(0).getNumericCellValue());// 假设第一列为 uid

                        choose.setSid((int) row.getCell(1).getNumericCellValue()); // 假设第二列为 sid
                        grade.setSid((int) row.getCell(1).getNumericCellValue()); // 假设第二列为 sid
                        System.out.println((int) row.getCell(1).getNumericCellValue());// 假设第一列为 uid
                        grade.setGrade(String.valueOf((float) row.getCell(2).getNumericCellValue()));
                        chooseMapper.insert(choose);


                    } catch (Exception e) {
                        e.printStackTrace(); }
                        finally {
                        gradeMapper.update(Float.parseFloat(grade.grade),grade.uid,grade.sid);
                    }
                }
                
                return ResponseEntity.ok("上传成功,请返回刷新");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.ok("数据库有重复数据,请返回刷新");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("上传失败,请返回刷新");
        }

    }

}
