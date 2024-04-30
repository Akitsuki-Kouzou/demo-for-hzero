package com.example.employee;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    public Long countById(Integer id);
}