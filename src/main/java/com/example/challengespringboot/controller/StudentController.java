package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.request.SearchData;
import com.example.challengespringboot.model.request.StudentRequest;
import com.example.challengespringboot.model.response.SuccessResponse;
import com.example.challengespringboot.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity createStudentController(@Valid @RequestBody StudentRequest studentRequest){
        Student newStudent = modelMapper.map(studentRequest, Student.class);

        Student student = studentService.createStudentService(newStudent);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("CREATE STUDENT SUCCESS", student));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdStudentController(@PathVariable("id") Long id){
        Optional<Student> studentList = studentService.findByIdService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Student>>("SUCCESS FINDING ID", studentList));
    }
    @GetMapping
    public ResponseEntity findAllStudentController(){
        Iterable<Student> students = studentService.findAllStudentService();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Student>>("SUCCESS GET ALL DATA STUDENTS", students));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateStudentController(@PathVariable Long id, @Valid @RequestBody StudentRequest studentRequest){
        Student newStudent = modelMapper.map(studentRequest, Student.class);

        Student student = studentService.updateStudentService(newStudent, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("UPDATE STUDENT SUCCESS", student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteByIdService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("DELETE STUDENT SUCCESS", "DATA NULL"));
    }

    @PostMapping("/{first_name}")
    public List<Student> findNameStudentController(@RequestBody SearchData searchData){
        return studentService.findNameStudentService(searchData.getSearchKey());
    }
//    @PostMapping("/search/{page}/{size}/{sort}")
//    public Iterable<Student> findByNameContainsController(@RequestBody SearchData searchData, @PathVariable int size, @PathVariable int page, @PathVariable("sort") String sort){
//        Pageable pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
//        if(sort.equals("desc")){
//            pageable = PageRequest.of(page-1, size, Sort.by("id").descending());
//        }
//        return studentService.findByNameContainsService(searchData.getSearchKey(), pageable);
//    }
}
