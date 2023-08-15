package com.study.school.service;


import com.study.school.Response.FullSchoolResponse;
import com.study.school.entity.School;
import com.study.school.repository.SchoolRepositiry;
import com.study.school.student.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepositiry schoolRepositiry;
    private final StudentClient studentClient;

    public void saveSchool(School school){
        schoolRepositiry.save(school);
    }

    public List<School> findAllSchool(){
        return schoolRepositiry.findAll();
    }

    public FullSchoolResponse findSchoolWithStudent(int schoolId) {
        var School = schoolRepositiry.findById(schoolId).orElse(
                com.study.school.entity.School.builder()
                        .name("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
        );
        var students = studentClient.findStudentsWithSchool(schoolId);
        return FullSchoolResponse.builder()
                .name(School.getName())
                .email(School.getEmail())
                .students(students)
                .build();
    }
}
