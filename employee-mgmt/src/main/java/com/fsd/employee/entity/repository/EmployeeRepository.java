package com.fsd.employee.entity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fsd.employee.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmployeeId(String employeeId);

    void deleteByEmployeeId(String employeeId);
}
