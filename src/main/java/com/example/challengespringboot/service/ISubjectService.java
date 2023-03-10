package com.example.challengespringboot.service;

import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.utils.SubjectKey;

import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    List<Subject> list();

    Subject create(Subject subject);
    Optional<Subject> get(String id);
    Optional<List<Subject>> getBy(SubjectKey key, String value);

}