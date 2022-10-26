package com.csmtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csmtech.model.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {

}
