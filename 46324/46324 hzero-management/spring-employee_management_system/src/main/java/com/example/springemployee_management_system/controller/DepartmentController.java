package com.example.springemployee_management_system.controller;

import com.example.springemployee_management_system.model.Department;
import com.example.springemployee_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentCode}")
    public ResponseEntity<Department> getDepartmentByCode(@PathVariable String departmentCode) {
        return departmentService.getDepartmentByCode(departmentCode)
                .map(department -> ResponseEntity.ok().body(department))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    @PutMapping("/{departmentCode}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String departmentCode, @RequestBody Department updatedDepartment) {
        return departmentService.getDepartmentByCode(departmentCode)
                .map(existingDepartment -> {
                    existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
                    existingDepartment.setDepartmentCode(updatedDepartment.getDepartmentCode());
                    Department savedDepartment = departmentService.saveDepartment(existingDepartment);
                    return ResponseEntity.ok().body(savedDepartment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{departmentCode}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Iterable<Department> searchDepartmentsByName(@RequestParam String name) {
        return departmentService.searchDepartmentsByName(name);
    }
}
