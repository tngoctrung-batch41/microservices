package com.study.school.controller;

import com.study.school.Response.FullSchoolResponse;
import com.study.school.Student;
import com.study.school.entity.School;
import com.study.school.service.SchoolService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity saveSchool(@RequestBody School school){
        schoolService.saveSchool(school);
        return ResponseEntity.ok("Add School Success");
    }

    @GetMapping
    public ResponseEntity<List<School>> findAll (){
        return ResponseEntity.ok(schoolService.findAllSchool());
    }

    @GetMapping("/withstudent/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudent(@PathVariable int schoolId){
        return ResponseEntity.ok(schoolService.findSchoolWithStudent(schoolId));
    }

}
