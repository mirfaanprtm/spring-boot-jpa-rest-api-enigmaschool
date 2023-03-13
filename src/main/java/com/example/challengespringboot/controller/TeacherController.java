package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.model.response.SuccessResponse;
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Teacher>>("SUCCESS FINDING", teacherList));
    }
    @PostMapping
    public ResponseEntity createTeacher(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Teacher>("CREATE SUCCESS", teacher1));
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Teacher> teacher = teacherService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Teacher>>("ID FOUND", teacher));
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Teacher>> teachers = teacherService.getBy(TeacherStudentKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Teacher>>>("TEACHER FOUND", teachers));
    }
    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody String id){
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete Success", "null"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Teacher teacher, @PathVariable String id){
        teacherService.update(teacher, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("Update Success", teacher));
    }

    @PostMapping("/addBulk")
    public ResponseEntity addBulk(@RequestBody List<Teacher> teachers){
        List<Teacher> teacherList = teacherService.addBulk(teachers);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<List<Teacher>>("CREATE SUCCESS", teacherList));
    }
}
