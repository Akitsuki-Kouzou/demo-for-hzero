package com.example.springdemo.controller;

import com.example.springdemo.model.Employee;
import com.example.springdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.searchByName(name);
    }
//    @GetMapping("/{employeeCode}")
//    public Employee getEmployeeById(@PathVariable String employeeCode) {
//        // Add logic to retrieve an employee (consider error handling if not found)
//        return employeeService.findEmployeeById(employeeCode);
//    }

    @PutMapping("/{employeeCode}")
    public Employee updateEmployee(@PathVariable String employeeCode, @RequestBody Employee newEmployee) {
        // Add logic to update an employee (consider error handling if not found)
        return employeeService.updateEmployee(newEmployee);
    }

    @DeleteMapping("/{employeeCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indicates successful deletion (no body)
    public void deleteEmployee(@PathVariable String employeeCode) {
        employeeService.deleteEmployee(employeeCode);
    }
}
