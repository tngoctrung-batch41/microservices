package com.study.student.controller;

import com.study.student.entity.Student;
import com.study.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return ResponseEntity.ok("New student is added");
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll (){
        return ResponseEntity.ok(studentService.findAllStudent());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Student>> findAllStudent (@PathVariable int schoolId){
        return ResponseEntity.ok(studentService.findStudentsBySchool(schoolId));
    }
}
