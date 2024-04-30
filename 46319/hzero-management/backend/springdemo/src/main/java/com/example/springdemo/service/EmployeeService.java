package com.example.springdemo.service;

import com.example.springdemo.model.Employee;
import com.example.springdemo.repository.EmployeeRepo;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAllEmployees();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.saveEmployee(employee); // Assuming a method in your repo
    }

    public Employee updateEmployee(Employee employee) {
        // ... Logic to update (likely calling an update method in your repo)
        employeeRepo.updateEmployee(employee);
        return employee;
    }

    public void deleteEmployee(String employeeCode) {
        employeeRepo.deleteEmployee(employeeCode); // Assuming a method in your repo
    }
    public List<Employee> searchByName(String name) {
        List<Employee> allEmployees = employeeRepo.findAllEmployees();
        List<Employee> fuzzySearchResult = new ArrayList<>();

        JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();

        for (Employee employee : allEmployees) {
            double similarityScore = jaroWinkler.apply(employee.getEmployee_name(), name);
            if (similarityScore >= 0.75) { // You can adjust this threshold
                fuzzySearchResult.add(employee);
            }
        }

        return fuzzySearchResult;
    }
}
