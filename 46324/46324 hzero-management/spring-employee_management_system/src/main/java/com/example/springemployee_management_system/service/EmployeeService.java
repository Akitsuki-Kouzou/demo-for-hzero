package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Employee;
import com.example.springemployee_management_system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Iterable<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(keyword);
    }
}
