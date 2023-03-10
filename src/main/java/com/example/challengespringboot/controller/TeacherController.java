package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.service.IStudentService;
import com.example.challengespringboot.service.ITeacherService;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    ITeacherService teacherService;

    @GetMapping
    public ResponseEntity getAllTeacher(){
        List<Teacher> teacherList = teacherService.list();
        if(teacherList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Kosong");
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacherList);
    }
    @PostMapping
    public ResponseEntity createTeacher(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher1);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Teacher> teacher = teacherService.get(id);
        if(teacher.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Teacher>> teachers = teacherService.getBy(TeacherStudentKey.valueOf(key), value);
        if(teachers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }
}
