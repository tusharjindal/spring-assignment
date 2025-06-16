package spring.assignment.assignment.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Department {
    
    @Id @GeneratedValue
    private Long id;    
    private String name;
    private Double budget;

}
