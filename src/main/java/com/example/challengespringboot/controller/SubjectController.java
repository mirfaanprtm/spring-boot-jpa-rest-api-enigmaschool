package com.example.challengespringboot.controller;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
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
        if(subjectList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Kosong");
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectList);
    }
    @PostMapping
    public ResponseEntity createSubject(@RequestBody Subject subject){
        Subject subject1 = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subject1);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id){
        Optional<Subject> subject = subjectService.get(id);
        if(subject.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam String key, @RequestParam String value){
        Optional<List<Subject>> subjects = subjectService.getBy(SubjectKey.valueOf(key), value);
        if(subjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data tidak ditemukan");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subjects);
    }
}
