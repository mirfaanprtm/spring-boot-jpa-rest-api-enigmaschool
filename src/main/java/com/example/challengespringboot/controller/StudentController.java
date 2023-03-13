package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.response.SuccessResponse;
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
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Student>>("SUCCESS FINDING", studentList));
    }
    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){
        Student student1 = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Student>("CREATE SUCCESS", student1));
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Student> student = studentService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Student>>("ID FOUND", student));
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Student>> students = studentService.getBy(TeacherStudentKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Student>>>("STUDENT FOUND", students));
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody String id){
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Delete Success", "null"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Student student, @PathVariable String id){
        studentService.update(student, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("Update Success", student));
    }

    @PostMapping("/addBulk")
    public ResponseEntity addBulk(@RequestBody List<Student> students){
        List<Student> studentList = studentService.addBulk(students);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<List<Student>>("CREATE SUCCESS", studentList));
    }
}
