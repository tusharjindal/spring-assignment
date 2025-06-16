package spring.assignment.assignment.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.assignment.assignment.Models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
