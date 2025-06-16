package spring.assignment.assignment.Dtos;

import java.util.List;

public record EmployeeDetailsDto(
    Long id, 
    String name, 
    String email, 
    String department,
    List<String> projects, 
    List<PerformanceReviewDto> last3Reviews) {
                                    
}
