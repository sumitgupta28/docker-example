package com.fsd.employee.controller;

import com.fsd.employee.entity.Employee;
import com.fsd.employee.entity.repository.EmployeeRepository;
import com.fsd.employee.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employees", description = "Endpoints for managing Employees")
@Slf4j
@AllArgsConstructor
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/employees", produces = "application/json")
    @Operation(summary = "Returns list of all Employees in the system.")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        log.info(" All the Employees");
        return employees;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/employees/{employeeId}", produces = "application/json")
    @Operation(summary = "Returns a specific Employee by their identifier. 404 if does not exist.")
    public ResponseEntity<Employee> getEmployeeById(
            @Parameter(description = "Id of the employee to be obtained. Cannot be empty.") @PathVariable(value = "employeeId") String employeeId)
            throws ResourceNotFoundException {
        Employee employee = findEmployeeById(employeeId);
        log.info(" Employee {} by Employee Id {} ", employee, employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/employees", produces = "application/json")
    @Operation(summary = "Creates a new Employee.")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        log.info(" Creating new Employee {}", employee);
        return employeeRepository.save(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "employeeId") String employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = findEmployeeById(employeeId);
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        log.info(" Update Employee {}", employee);
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/employees/{id}")
    @Operation(summary = "Deletes a employee from the system. 404 if the employee identifier is not found.")
    public Map<String, Boolean> deleteEmployee(
            @Parameter(description = "Id of the Employee to be deleted. Cannot be empty.") @PathVariable(value = "id") String employeeId)
            throws ResourceNotFoundException {
        Employee employee = findEmployeeById(employeeId);
        log.info(" Delete Employee {} by EmployeeId {} ", employee, employeeId);
        employeeRepository.deleteByEmployeeId(employee.getEmployeeId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Employee findEmployeeById(String employeeId) throws ResourceNotFoundException {
        return employeeRepository.findByEmployeeId(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this employeeId :: " + employeeId));
    }

}
