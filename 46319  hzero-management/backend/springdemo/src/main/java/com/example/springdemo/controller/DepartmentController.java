package com.example.springdemo.controller;

import com.example.springdemo.model.Department;
import com.example.springdemo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments") // Base URL for all Department endpoints
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/search")
    public List<Department> searchByName(@RequestParam String name) {
        return departmentService.searchByName(name);
    }

//    @GetMapping("/{departmentCode}")
//    public Department getDepartmentById(@PathVariable String departmentCode) {
//        return departmentService.findDepartmentById(departmentCode); // Assuming a method in your service
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/{departmentCode}")
    public Department updateDepartment(@PathVariable String departmentCode, @RequestBody Department newDepartment) {
        // ... (Add logic to update)
        return departmentService.updateDepartment(newDepartment);
    }

    @DeleteMapping("/{departmentCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
    }
}
