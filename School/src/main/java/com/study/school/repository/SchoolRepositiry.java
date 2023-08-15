package com.study.school.repository;

import com.study.school.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepositiry extends JpaRepository<School,Integer> {
}
