package com.example.university.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.university.domain.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

  List<Student> findByFullTime(boolean fullTime);

  List<Student> findByAge(Integer age);

  List<Student> findByAttendeeLastName(String lastName);

  // Queries with clauses and expressions
  // findOldest
  @Query(value = "SELECT * FROM student s ORDER BY s.age DESC LIMIT 1", nativeQuery = true)
  Optional<Student> findOldest();

  // findByFirstAndLastName
  @Query("SELECT s FROM Student s WHERE s.attendee.firstName = :firstName and s.attendee.lastName = :lastName")
  List<Student> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

  // findByAgeLessThan
  @Query("SELECT s FROM Student s where s.age < :age")
  List<Student> findByAgeLessThan(@Param("age") int age);

  // findSimilarLastName
  List<Student> findByAttendeeLastNameLike(String nameCriteria);

  // findFirstInAlphabet
  @Query(value = "SELECT * FROM STUDENT s ORDER BY s.last_Name ASC LIMIT 1", nativeQuery = true)
  Optional<Student> findFirstInAlphabet();

  // find3Oldest
  @Query(value = "SELECT * FROM STUDENT s  ORDER BY s.age DESC LIMIT 3", nativeQuery = true)
  List<Student> find3Oldest();

}
