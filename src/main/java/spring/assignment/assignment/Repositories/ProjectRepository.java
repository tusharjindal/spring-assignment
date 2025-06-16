package spring.assignment.assignment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.assignment.assignment.Models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
