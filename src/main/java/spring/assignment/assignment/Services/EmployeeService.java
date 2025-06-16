package spring.assignment.assignment.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import spring.assignment.assignment.Dtos.EmployeeDetailsDto;
import spring.assignment.assignment.Dtos.EmployeeSummaryDto;
import spring.assignment.assignment.Dtos.PerformanceReviewDto;
import spring.assignment.assignment.Models.Employee;
import spring.assignment.assignment.Models.PerformanceReview;
import spring.assignment.assignment.Models.Project;
import spring.assignment.assignment.Repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired private EmployeeRepository employeeRepo;

        public List<EmployeeSummaryDto> filterEmployees(LocalDate reviewDate, Double minScore,
                                                    List<String> departments, List<String> projects) {
        return employeeRepo.findAll((root, query, cb) -> {
            root.fetch("department", JoinType.LEFT);
            root.fetch("projects", JoinType.LEFT);
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            if (departments != null && !departments.isEmpty()) {
                predicates.add(root.get("department").get("name").in(departments));
            }
            if (projects != null && !projects.isEmpty()) {
                predicates.add(root.join("projects").get("name").in(projects));
            }
            if (reviewDate != null && minScore != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<PerformanceReview> pr = subquery.from(PerformanceReview.class);
                subquery.select(pr.get("employee").get("id"))
                        .where(cb.and(
                                cb.equal(pr.get("reviewDate"), reviewDate),
                                cb.greaterThanOrEqualTo(pr.get("score"), minScore)
                        ));
                predicates.add(root.get("id").in(subquery));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }).stream().map(emp -> new EmployeeSummaryDto(
                emp.getId(), emp.getName(), emp.getEmail(),
                emp.getDepartment() != null ? emp.getDepartment().getName() : null,
                emp.getProjects().stream().map(Project::getName).toList()
        )).toList();
    }

    public EmployeeDetailsDto getEmployeeDetails(Long id) {
        Employee emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        List<PerformanceReviewDto> last3 = emp.getReviews().stream()
                .sorted((a, b) -> b.getReviewDate().compareTo(a.getReviewDate()))
                .limit(3)
                .map(r -> new PerformanceReviewDto(r.getReviewDate(), r.getScore(), r.getReviewComments()))
                .toList();
        return new EmployeeDetailsDto(emp.getId(), emp.getName(), emp.getEmail(),
                emp.getDepartment() != null ? emp.getDepartment().getName() : null,
                emp.getProjects().stream().map(Project::getName).toList(),
                last3);
    }
}
