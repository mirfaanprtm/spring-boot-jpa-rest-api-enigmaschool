package com.example.challengespringboot.service;

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
            return subjectRepository.getAll();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Subject create(Subject subject) {
        try {
            return subjectRepository.create(subject);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Subject> get(String id) {
        try {
            return subjectRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Subject>> getBy(SubjectKey key, String value) {
        try {
            return subjectRepository.findBy(key, value);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
