package com.example.hzeromanagement.Repository;

import com.example.hzeromanagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByEmployeeNameLike(String empName);

}
