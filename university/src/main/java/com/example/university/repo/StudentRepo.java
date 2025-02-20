package com.example.university.repo;

import com.example.university.domain.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

  List<Student> findByFullTime(boolean fullTime);

  List<Student> findByAge(Integer age);

  List<Student> findByAttendeeLastName(String lastName);
}
