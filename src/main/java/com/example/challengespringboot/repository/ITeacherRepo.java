package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepo extends JpaRepository<Teacher, Long> {

}
