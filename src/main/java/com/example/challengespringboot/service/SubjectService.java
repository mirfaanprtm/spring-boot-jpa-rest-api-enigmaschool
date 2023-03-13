package com.example.challengespringboot.service;

import com.example.challengespringboot.exception.NotFoundException;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.repository.IStudentRepository;
import com.example.challengespringboot.repository.ISubjectRepository;
import com.example.challengespringboot.utils.SubjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SubjectService implements ISubjectService{
    @Autowired
    private ISubjectRepository subjectRepository;
    @Override
    public List<Subject> list() {
        try {
            List<Subject> subjects = subjectRepository.getAll();
            if(subjects.isEmpty()){
                throw new NotFoundException("Subject Is Empty");
            }
            return subjects;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject create(Subject subject) {
        try {
            Optional<List<Subject>> subjects = subjectRepository.findBy(SubjectKey.subject_name, subject.getSubject_name());
            if(subjectRepository.getAll().size() <= 5){
                if (subjects.isPresent()){
                    throw new Exception("Data Already Exist");
                }
                return subjectRepository.create(subject);
            } else {
                throw new NotFoundException("Data is Full");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> get(String id) {
        try {
            Optional<Subject> subject = subjectRepository.findById(id);
            if(subject.isEmpty()){
                throw new NotFoundException("Subject ID Not Found");
            }
            return subject;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Subject>> getBy(SubjectKey key, String value) {
        try {
            Optional<List<Subject>> subjects = subjectRepository.findBy(key, value);
            if(subjects.isEmpty()){
                throw new NotFoundException("Subject Not Found");
            }
            return subjects;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject update(Subject subject, String id) {
        try {
            get(id);
            subjectRepository.update(subject, id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return subject;
    }

    @Override
    public void delete(String id) {
        try {
            get(id);
            subjectRepository.delete(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Subject> addBulk(List<Subject> bulkSubjects) {
        try {
            return subjectRepository.addBulk(bulkSubjects);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
