package com.example.springemployee_management_system.controller;

import com.example.springemployee_management_system.model.Employee;
import com.example.springemployee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee updatedEmployee) {
        return employeeService.getEmployeeById(employeeId)
                .map(existingEmployee -> {
                    existingEmployee.setEmployeeCode(updatedEmployee.getEmployeeCode());
                    existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
                    existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());
                    existingEmployee.setDepartmentCode(updatedEmployee.getDepartmentCode());
                    existingEmployee.setPositionCode(updatedEmployee.getPositionCode());
                    Employee savedEmployee = employeeService.saveEmployee(existingEmployee);
                    return ResponseEntity.ok().body(savedEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Iterable<Employee> searchEmployees(@RequestParam String keyword) {
        return employeeService.searchEmployees(keyword);
    }

}
