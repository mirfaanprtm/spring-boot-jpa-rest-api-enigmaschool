package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.utils.IRandomStringGenerator;
import com.example.challengespringboot.utils.TeacherStudentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentArrayRepository implements IStudentRepository{
    private TeacherStudentKey teacherStudentKey;
    @Autowired
    IRandomStringGenerator randomStringGenerator;
    private List<Student> students = new ArrayList<>();
    @Override
    public List<Student> getAll() throws Exception {
        return students;
    }

    @Override
    public Student create(Student student) throws Exception {
        student.setStudentId(randomStringGenerator.random());
        students.add(student);
        return student;
    }

    @Override
    public Optional<Student> findById(String id) throws Exception {
        for(Student student : students){
            if(student.getStudentId().equals(id)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Student>> findBy(TeacherStudentKey key, String value) throws Exception {
        List<Student> studentList = new ArrayList<>();
        for(Student student : students){
            switch (key){
                case first_name -> {
                    if(student.getFirst_name().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }
                case last_name -> {
                    if(student.getLast_name().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }
                case email -> {
                    if(student.getEmail().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }
            }
        }
        return studentList.isEmpty() ? Optional.empty() : Optional.of(studentList);
    }
}
