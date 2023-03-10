package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.service.IStudentService;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    IStudentService studentService;

    @GetMapping
    public ResponseEntity getAllCourseStudent(){

        List<Student> studentList = studentService.list();
        if(studentList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Kosong");
        }

        return ResponseEntity.status(HttpStatus.OK).body(studentList);
    }
    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){
        Student student1 = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Student> student = studentService.get(id);
        if(student.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Student>> students = studentService.getBy(TeacherStudentKey.valueOf(key), value);
        if(students.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}
