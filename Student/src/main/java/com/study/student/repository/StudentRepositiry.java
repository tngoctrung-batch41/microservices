package com.study.student.repository;

import com.study.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositiry extends JpaRepository<Student,Integer> {
    List<Student> findAllBySchoolId(int schoolId);
}
