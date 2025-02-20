package com.example.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.university.domain.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}