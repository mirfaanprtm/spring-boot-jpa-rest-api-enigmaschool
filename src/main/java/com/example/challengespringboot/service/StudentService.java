package com.example.challengespringboot.service;

import com.example.challengespringboot.exception.NotFoundException;
import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private IStudentRepo studentRepo;

    public Student createStudentService(Student student){
        try {
            return studentRepo.save(student);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAllStudentService(){
        try {
            List<Student> students = studentRepo.findAll();
            if(students.isEmpty()){
                throw new NotFoundException("Data Not Found");
            }
            return students;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> findByIdService(Long id){
        try {
            Optional<Student> student = studentRepo.findById(id);
            if(student.isEmpty()){
                throw new NotFoundException("Student ID Not Found");
            }
            return student;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public Student updateStudentService(Student student, Long id){
        try {
            Optional<Student> student1 = studentRepo.findById(id);
            student1.get().setFirst_name(student.getFirst_name());
            student1.get().setLast_name(student.getLast_name());
            student1.get().setEmail(student.getEmail());
            return studentRepo.save(student1.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void deleteByIdService(Long id){
        try {
            findByIdService(id);
            studentRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Student> findNameStudentService(String first_name){
        return studentRepo.findByFirst_name('%' + first_name + '%');
    }
}
