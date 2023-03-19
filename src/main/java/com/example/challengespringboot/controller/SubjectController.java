package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.model.request.SearchData;
import com.example.challengespringboot.model.request.SubjectRequest;
import com.example.challengespringboot.model.request.TeacherRequest;
import com.example.challengespringboot.model.response.SuccessResponse;
import com.example.challengespringboot.service.SubjectService;
import com.example.challengespringboot.service.TeacherService;
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
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SubjectService subjectService;
    @PostMapping
    public ResponseEntity createSubjectController(@Valid @RequestBody SubjectRequest subjectRequest){
        Subject newSubject = modelMapper.map(subjectRequest, Subject.class);

        Subject subject = subjectService.createSubjectService(newSubject);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("CREATE SUBJECT SUCCESS", subject));
    }
    @GetMapping("/{id}")
    public ResponseEntity findByIdSubjectController(@PathVariable("id") Long id){
        Optional<Subject> subjectList = subjectService.findByIdSubjectService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Subject>>("SUCCESS FINDING ID", subjectList));
    }
    @GetMapping
    public ResponseEntity findAllSubjectController(){
        Iterable<Subject> subjects = subjectService.findAllSubjectService();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Iterable<Subject>>("SUCCESS GET ALL DATA SUBJECTS", subjects));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateSubjectController(@PathVariable Long id, @Valid @RequestBody SubjectRequest subjectRequest){
        Subject newSubject = modelMapper.map(subjectRequest, Subject.class);

        Subject subject = subjectService.updateSubjectService(newSubject, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("UPDATE SUBJECT SUCCESS", subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubjectById(@PathVariable("id") Long id){
        subjectService.deleteSubjectByIdService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("DELETE SUBJECT SUCCESS", "DATA NULL"));
    }

    @PostMapping("/search/{page}/{size}/{sort}")
    public Iterable<Subject> findByNameContainsController(@RequestBody SearchData searchData, @PathVariable int page, @PathVariable int size, @PathVariable("sort") String sort){
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").ascending());
        if(pageable.equals("desc")){
            pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        }
        return subjectService.findByNameContainsService(searchData.getSearchKey(), pageable);
    }

    @GetMapping("/search/teacher/{teacher_id}")
    public List<Subject> findByTeacherIdController(@PathVariable("teacher_id") Long teacher){
        return subjectService.findByIdTeacherService(teacher);
    }

    @GetMapping("/search/student/{studentId}")
    public List<Subject> findByStudent(@PathVariable("studentId") Long studentId){
        return subjectService.findByStudentService(studentId);
    }
}
