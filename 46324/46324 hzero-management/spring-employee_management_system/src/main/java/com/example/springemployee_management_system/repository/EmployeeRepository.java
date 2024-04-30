package com.example.springemployee_management_system.repository;

import com.example.springemployee_management_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Iterable<Employee> findByEmployeeNameContainingIgnoreCase(String keyword);
}
