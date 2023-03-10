package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.utils.SubjectKey;
import com.example.challengespringboot.utils.TeacherStudentKey;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {
    List<Subject> getAll() throws Exception;
    Subject create(Subject subject) throws Exception;
    Optional<Subject> findById(String id) throws Exception;
    Optional<List<Subject>> findBy(SubjectKey key, String value) throws Exception;
}
