package com.example.challengespringboot.model.request;


import com.example.challengespringboot.model.Student;
import com.example.challengespringboot.model.Teacher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SubjectRequest {
    @NotBlank(message = "{invalid.subject_name.required}")
    @Size(message = "subject name must be between {min} and {max}", max = 100, min = 3)
    private String name;
    private Teacher teacher;

    private Set<Student> student;

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
