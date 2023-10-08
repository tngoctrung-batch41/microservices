package com.study.student.service;

import com.study.student.entity.Student;
import com.study.student.repository.StudentRepositiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepositiry studentRepositiry;

    public void saveStudent(Student student){
        studentRepositiry.save(student);
    }

    public List<Student> findAllStudent(){
        return studentRepositiry.findAll();
    }

    public List<Student> findStudentsBySchool(int schoolId) {
        return studentRepositiry.findAllBySchoolId(schoolId);
    }
}
