package com.example.challengespringboot.service;

import com.example.challengespringboot.exception.NotFoundException;
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
            List<Student> students = studentRepository.getAll();
            if(students.isEmpty()){
                throw new NotFoundException("Student Is Empty");
            }
            return students;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student create(Student student) {
        try {
            Optional<List<Student>> students = studentRepository.findBy(TeacherStudentKey.first_name, student.getFirst_name());
            if(studentRepository.getAll().size() <= 24){
                if (students.isPresent()){
                    throw new Exception("Data Already Exist");
                }
                return studentRepository.create(student);
            } else {
                throw new NotFoundException("Data is Full");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> get(String id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if(student.isEmpty()){
            throw new NotFoundException("Student ID Not Found");
            }
            return student;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Student>> getBy(TeacherStudentKey key, String value) {
        try {
            Optional<List<Student>> students = studentRepository.findBy(key, value);
            if(students.isEmpty()){
                throw new NotFoundException("Student Not Found");
            }
            return students;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student update(Student student, String id) {
        try {
            get(id);
            studentRepository.update(student, id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return student;
    }

    @Override
    public void delete(String id) {
        try {
            get(id);
            studentRepository.delete(id);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Student> addBulk(List<Student> bulkStudents) {
        try {
            return studentRepository.addBulk(bulkStudents);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
