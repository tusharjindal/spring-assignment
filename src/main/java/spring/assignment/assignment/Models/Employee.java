package spring.assignment.assignment.Models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Employee {

    @Id @GeneratedValue
    private Long id;    
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfJoining;
    private Double salary;

    @ManyToOne  
    private Department department;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy = "employee")
    private List<PerformanceReview> reviews;

    @ManyToMany
    @JoinTable(name = "employee_project",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects = new ArrayList<>();
}
