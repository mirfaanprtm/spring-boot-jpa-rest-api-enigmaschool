package com.example.challengespringboot.service;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import com.example.challengespringboot.utils.SubjectKey;
import com.example.challengespringboot.utils.TeacherStudentKey;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> list();
    Student create(Student student);
    Optional<Student> get(String id);
    Optional<List<Student>> getBy(TeacherStudentKey key, String value);
    Student update(Student student, String id);
    void delete(String id);
    List<Student> addBulk(List<Student> bulkStudents);
}
