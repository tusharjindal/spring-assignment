package spring.assignment.assignment.Dtos;

import java.time.LocalDate;

public record PerformanceReviewDto(LocalDate reviewDate, Double score, String reviewComments) {
    
}

