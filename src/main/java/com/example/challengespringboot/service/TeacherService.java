package com.example.challengespringboot.service;

import com.example.challengespringboot.exception.NotFoundException;
import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.repository.IStudentRepo;
import com.example.challengespringboot.repository.ITeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private ITeacherRepo teacherRepo;

    public Teacher createTeacherService(Teacher teacher){
        try {
            return teacherRepo.save(teacher);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> findAllTeacherService(){
        try {
            List<Teacher> teachers = teacherRepo.findAll();
            if(teachers.isEmpty()){
                throw new NotFoundException("Data Not Found");
            }
            return teachers;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Teacher> findByIdTeacherService(Long id){
        try {
            Optional<Teacher> teacher = teacherRepo.findById(id);
            if(teacher.isEmpty()){
                throw new NotFoundException("Teacher ID Not Found");
            }
            return teacher;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Teacher updateTeacherService(Teacher teacher, Long id){
        try {
            Optional<Teacher> teacher1 = teacherRepo.findById(id);
            teacher1.get().setFirst_name(teacher.getFirst_name());
            teacher1.get().setLast_name(teacher.getLast_name());
            teacher1.get().setEmail(teacher.getEmail());
            return teacherRepo.save(teacher1.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteByIdTeacherService(Long id){
        try {
            findByIdTeacherService(id);
            teacherRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
