package spring.assignment.assignment.Dtos;

import java.util.List;

public record EmployeeSummaryDto(Long id, String name, String email, String departmentName, List<String> projectNames) {

    
}
