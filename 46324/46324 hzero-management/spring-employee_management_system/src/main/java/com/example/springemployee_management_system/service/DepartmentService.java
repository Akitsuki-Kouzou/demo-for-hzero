package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Department;
import com.example.springemployee_management_system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentByCode(String departmentCode) {
        return departmentRepository.findById(departmentCode);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(String departmentCode) {
        departmentRepository.deleteById(departmentCode);
    }

    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByDepartmentNameContaining(name);
    }
}
