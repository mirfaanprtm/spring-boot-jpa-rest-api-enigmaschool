package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;
import com.example.challengespringboot.utils.IRandomStringGenerator;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherArrayRepository implements ITeacherRepository{
    private TeacherStudentKey teacherStudentKey;
    @Autowired
    IRandomStringGenerator randomStringGenerator;
    private List<Teacher> teachers = new ArrayList<>();
    @Override
    public List<Teacher> getAll() throws Exception {
        return teachers;
    }

    @Override
    public Teacher create(Teacher teacher) throws Exception {
        teacher.setTeacherId(randomStringGenerator.random());
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public Optional<Teacher> findById(String id) throws Exception {
        for(Teacher teacher : teachers){
            if(teacher.getTeacherId().equals(id)){
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Teacher>> findBy(TeacherStudentKey key, String value) throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        for(Teacher teacher : teachers){
            switch (key){
                case first_name -> {
                    if(teacher.getFirst_name().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }
                case last_name -> {
                    if(teacher.getLast_name().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }
                case email -> {
                    if(teacher.getEmail().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }
            }
        }
        return teacherList.isEmpty() ? Optional.empty() : Optional.of(teacherList);
    }
}
