package com.example.university.repo;

import com.example.university.domain.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepo extends JpaRepository<Course, Integer>{

   Optional<Course> findByName(String name);

    public List<Course> findByDepartmentChairMemberLastName(String chair);

    public List<Course> findByPrerequisites(Course course);

    public List<Course> findByCredits(int credits);
}
