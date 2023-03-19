package com.example.challengespringboot.repository;

import com.example.challengespringboot.model.Student;
import org.hibernate.sql.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.websocket.server.PathParam;
import java.util.List;

public interface IStudentRepo extends JpaRepository<Student, Long> {
    @Query("select p from Student p where p.first_name like :first_name")
    public List<Student> findByFirst_name(@PathParam("first_name") String first_name);
}
