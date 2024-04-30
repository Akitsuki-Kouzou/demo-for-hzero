package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class EmployeeDataInitializer {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeDataInitializer(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void initializeData() {
        Employee employee = new Employee();
        employee.setEmployeeCode("EMP001");
        employee.setEmployeeName("John Doe");
        employee.setJoiningDate(LocalDate.now());
        employee.setDepartmentCode("HR");
        employee.setPositionCode("HR_MANAGER");

        employeeService.saveEmployee(employee);
    }
}
