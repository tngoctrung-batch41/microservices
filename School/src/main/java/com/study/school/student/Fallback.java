package com.study.school.student;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Fallback implements StudentClient{
    @Override
    public List<Student> findStudentsWithSchool(int schoolId) {
        System.out.println("fallback called");
     return Collections.emptyList();
    }
}
