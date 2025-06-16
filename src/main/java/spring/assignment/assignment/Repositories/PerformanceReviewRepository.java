package spring.assignment.assignment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.assignment.assignment.Models.PerformanceReview;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long>{
    
}
