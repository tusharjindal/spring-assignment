package spring.assignment.assignment.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class PerformanceReview {
    
    @Id @GeneratedValue
    private Long id;    
    private LocalDate reviewDate;
    private Double score;
    private String reviewComments;

    @ManyToOne
    private Employee employee;
}
