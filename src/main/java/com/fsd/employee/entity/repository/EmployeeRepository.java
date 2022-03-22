package com.fsd.employee.entity.repository;

import com.fsd.employee.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmployeeId(String employeeId);

    void deleteByEmployeeId(String employeeId);
}
