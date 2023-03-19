package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.model.request.StudentRequest;
import com.example.challengespringboot.model.request.TeacherRequest;
import com.example.challengespringboot.model.response.SuccessResponse;
import com.example.challengespringboot.service.StudentService;
import com.example.challengespringboot.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TeacherService teacherService;
    @PostMapping
    public ResponseEntity createTeacherController(@Valid @RequestBody TeacherRequest teacherRequest){
        Teacher newTeacher = modelMapper.map(teacherRequest, Teacher.class);

        Teacher teacher = teacherService.createTeacherService(newTeacher);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("CREATE TEACHER SUCCESS", teacher));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdTeacherController(@PathVariable("id") Long id){
        Optional<Teacher> teacherList = teacherService.findByIdTeacherService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Teacher>>("SUCCESS FINDING ID", teacherList));
    }
    @GetMapping
    public ResponseEntity findAllStudentController(){
        Iterable<Teacher> teachers = teacherService.findAllTeacherService();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Teacher>>("SUCCESS GET ALL DATA STUDENTS", teachers));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateTeacherController(@PathVariable Long id, @Valid @RequestBody TeacherRequest teacherRequest){
        Teacher newTeacher = modelMapper.map(teacherRequest, Teacher.class);

        Teacher teacher = teacherService.updateTeacherService(newTeacher, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("UPDATE STUDENT SUCCESS", teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeacherById(@PathVariable("id") Long id){
        teacherService.deleteByIdTeacherService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("DELETE STUDENT SUCCESS", "DATA NULL"));
    }
}
