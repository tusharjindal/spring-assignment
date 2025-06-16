package spring.assignment.assignment.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.assignment.assignment.Dtos.EmployeeDetailsDto;
import spring.assignment.assignment.Dtos.EmployeeSummaryDto;
import spring.assignment.assignment.Services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeSummaryDto>> filterEmployees(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
            @RequestParam(required = false) Double minScore,
            @RequestParam(required = false) List<String> departments,
            @RequestParam(required = false) List<String> projects
    ) {
        return ResponseEntity.ok(employeeService.filterEmployees(reviewDate, minScore, departments, projects));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsDto> getEmployeeDetails(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
    }
}
