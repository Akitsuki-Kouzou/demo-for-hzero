package com.example.webapp.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    public long countByEmployeeId(Integer employeeId);
    List<Employee> findByEmployeeId(Integer employeeId);
}
