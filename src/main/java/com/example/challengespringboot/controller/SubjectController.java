package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.model.response.SuccessResponse;
import com.example.challengespringboot.service.IStudentService;
import com.example.challengespringboot.service.ISubjectService;
import com.example.challengespringboot.utils.SubjectKey;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    ISubjectService subjectService;

    @GetMapping
    public ResponseEntity getAllSubject(){
        List<Subject> subjectList = subjectService.list();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Subject>>("SUCCESS FINDING", subjectList));
    }
    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject){
        Subject subject1 = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Subject>("CREATE SUCCESS", subject1));
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Subject> subject = subjectService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Subject>>("ID FOUND", subject));
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Subject>> subjects = subjectService.getBy(SubjectKey.valueOf(key), value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Subject>>>("SUBJECT FOUND", subjects));
    }
    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody String id){
        subjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("DELETE SUCCESS", "null"));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Subject subject, @PathVariable String id){
        subjectService.update(subject, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("UPDATE SUCCESS", subject));
    }

    @PostMapping("/addBulk")
    public ResponseEntity addBulk(@RequestBody List<Subject> subjects){
        List<Subject> subjectList = subjectService.addBulk(subjects);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<List<Subject>>("CREATE SUCCESS", subjectList));
    }
}
