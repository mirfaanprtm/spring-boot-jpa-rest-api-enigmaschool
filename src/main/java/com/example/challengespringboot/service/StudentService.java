package com.example.challengespringboot.service;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.repository.IStudentRepository;
import com.example.challengespringboot.utils.SubjectKey;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> list() {
        try {
            return studentRepository.getAll();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Student create(Student student) {
        try {
            return studentRepository.create(student);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> get(String id) {
        try {
            return studentRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Student>> getBy(TeacherStudentKey key, String value) {
        try {
            return studentRepository.findBy(key, value);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
