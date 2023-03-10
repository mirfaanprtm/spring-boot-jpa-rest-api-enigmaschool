package com.example.challengespringboot.service;

import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.repository.IStudentRepository;
import com.example.challengespringboot.repository.ITeacherRepository;
import com.example.challengespringboot.utils.SubjectKey;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeacherService implements ITeacherService{
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public List<Teacher> list() {
        try {
            return teacherRepository.getAll();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Teacher create(Teacher teacher) {
        try {
            return teacherRepository.create(teacher);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Teacher> get(String id) {
        try {
            return teacherRepository.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Teacher>> getBy(TeacherStudentKey key, String value) {
        try {
            return teacherRepository.findBy(key, value);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
