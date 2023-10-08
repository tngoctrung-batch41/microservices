package com.study.school.service;


import com.study.school.Exception.ServiceException;
import com.study.school.Response.FullSchoolResponse;
import com.study.school.entity.School;
import com.study.school.repository.SchoolRepositiry;
import com.study.school.student.StudentClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.CheckedFunction0;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepositiry schoolRepositiry;
    private final StudentClient studentClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private static final String SERVICE_A = "serviceStudent";

    public void saveSchool(School school){
        schoolRepositiry.save(school);
    }

    public List<School> findAllSchool(){
        return schoolRepositiry.findAll();
    }


    public FullSchoolResponse findSchoolWithStudent(int schoolId) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry
                .circuitBreaker(SERVICE_A);
        try {
            var School = schoolRepositiry.findById(schoolId).orElseThrow(()->new NotFoundException("School NotFound"));
            var students = CircuitBreaker.decorateSupplier(circuitBreaker, () -> studentClient.findStudentsWithSchool(schoolId)).get();
            return FullSchoolResponse.builder()
                    .name(School.getName())
                    .email(School.getEmail())
                    .students(students)
                    .build();
        } catch (Exception e) {
            // Handle the circuit breaker open state
            return handleCircuitBreakerOpenStateOrFallback(e);
        }

//        var School = schoolRepositiry.findById(schoolId).orElseThrow(()->new NotFoundException("School NotFound"));
//        var students = studentClient.findStudentsWithSchool(schoolId);
//        System.out.println(students);
//        return FullSchoolResponse.builder()
//                .name(School.getName())
//                .email(School.getEmail())
//                .students(students)
//                .build();
    }
    public FullSchoolResponse handleCircuitBreakerOpenStateOrFallback(Exception e){
        throw new ServiceException("Service Student is not available or not response now, please try later");
    }

}
