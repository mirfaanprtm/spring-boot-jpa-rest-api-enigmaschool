package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.utils.IRandomStringGenerator;
import com.example.challengespringboot.utils.SubjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectArrayRepository implements ISubjectRepository{
    private SubjectKey subjectKey;
    @Autowired
    IRandomStringGenerator randomStringGenerator;
    private List<Subject> subjects = new ArrayList<>();
    @Override
    public List<Subject> getAll() throws Exception {
        return subjects;
    }

    @Override
    public Subject create(Subject subject) throws Exception {
        subject.setSubjectId(randomStringGenerator.random());
        subjects.add(subject);
        return subject;
    }

    @Override
    public Optional<Subject> findById(String id) throws Exception {
        for(Subject subject : subjects){
            if(subject.getSubjectId().equals(id)){
                return Optional.of(subject);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Subject>> findBy(SubjectKey key, String value) throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        for(Subject subject : subjects){
            switch (key){
                case subject_name -> {
                    if(subject.getSubject_name().toLowerCase().contains(value)){
                        subjectList.add(subject);
                    }
                }
            }
        }
        return subjectList.isEmpty() ? Optional.empty() : Optional.of(subjectList);
    }
}
