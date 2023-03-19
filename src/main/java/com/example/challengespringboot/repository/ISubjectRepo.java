package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public interface ISubjectRepo extends JpaRepository<Subject, Long> {
    Page<Subject> findByNameContains(String subject_name, Pageable pageable);

    @Query("select s from Subject s where s.teacher.id = :teacher_id")
    public List<Subject> findByTeacherId(@PathParam("teacher_id") Long teacher_id);

    @Query("select s from Subject s where :student member of s.student")
    public List<Subject> findByStudent(@PathParam("student") Optional<Student> student);
}
