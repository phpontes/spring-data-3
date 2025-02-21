package com.example.university.business;

import com.example.university.dao.CourseDao;
import com.example.university.domain.Course;
import com.example.university.repo.CourseRepo;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    private EntityManagerFactory emf;

    private EntityManager em;

    public DynamicQueryService(CourseRepo courseRepo, EntityManagerFactory emf) {
        this.courseRepo = courseRepo;
        this.em = emf.createEntityManager();
    }

    public List<Course> filterBySpecification(CourseFilter filter) {
        return courseRepo.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filter.getDepartment().ifPresent(d -> predicates.add(criteriaBuilder.equal(root.get("department"), d)));
            filter.getCredits().ifPresent(c -> predicates.add(criteriaBuilder.equal(root.get("credits"), c)));
            filter.getInstructor().ifPresent(i -> predicates.add(criteriaBuilder.equal(root.get("instructor"), i)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
