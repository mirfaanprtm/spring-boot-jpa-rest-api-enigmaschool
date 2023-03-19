package com.example.challengespringboot.service;

import com.example.challengespringboot.exception.NotFoundException;
import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.repository.IStudentRepo;
import com.example.challengespringboot.repository.ISubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {
    @Autowired
    private ISubjectRepo subjectRepo;

    @Autowired
    StudentService studentService;
    public Subject createSubjectService(Subject subject){
        try {
            return subjectRepo.save(subject);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Subject> findAllSubjectService(){
        try {
            List<Subject> subjects = subjectRepo.findAll();
            if(subjects.isEmpty()){
                throw new NotFoundException("Data Not Found");
            }
            return subjects;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Subject> findByIdSubjectService(Long id){
        try {
            Optional<Subject> subject = subjectRepo.findById(id);
            if(subject.isEmpty()){
                throw new NotFoundException("Subject ID Not Found");
            }
            return subject;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Subject updateSubjectService(Subject subject, Long id){
        try {
            Optional<Subject> subject1 = subjectRepo.findById(id);
            subject1.get().setName(subject.getName());
            return subjectRepo.save(subject1.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteSubjectByIdService(Long id){
        try {
            findByIdSubjectService(id);
            subjectRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Iterable<Subject> findByNameContainsService(String name, Pageable pageable){
        return subjectRepo.findByNameContains(name, pageable);
    }

    public List<Subject> findByIdTeacherService(Long teacher){
        return subjectRepo.findByTeacherId(teacher);
    }
    public List<Subject> findByStudentService(Long studentId){
        Optional<Student> student = studentService.findByIdService(studentId);
        if(student.isEmpty()){
            throw new RuntimeException("ID NOT FOUND");
        }
        return subjectRepo.findByStudent(student);
    }
}
