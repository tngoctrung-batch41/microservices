package com.study.school.Response;

import com.study.school.student.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullSchoolResponse {
    private String name;
    private String email;
    private List<Student> students;
    private String fallbackStatement;
}
