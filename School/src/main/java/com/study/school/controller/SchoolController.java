package com.study.school.controller;

import com.study.school.Exception.ServiceException;
import com.study.school.Response.FullSchoolResponse;
import com.study.school.entity.School;
import com.study.school.service.SchoolService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/create")
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
        checkCircuitBreakerHealth();
        return ResponseEntity.ok(schoolService.findSchoolWithStudent(schoolId));
    }

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    public void checkCircuitBreakerHealth() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("serviceStudent");
        if (circuitBreaker.getState() == CircuitBreaker.State.CLOSED) {
            System.err.println("Circuit Breaker is closed (service is healthy)");
        } else if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
            System.err.println("Circuit Breaker is opened (service is unhealthy)");
            throw new ServiceException("Circuit Breaker is closed (service is healthy)");
        } else {
            System.err.println("Circuit Breaker is in half-open state");
        }
    }

}
