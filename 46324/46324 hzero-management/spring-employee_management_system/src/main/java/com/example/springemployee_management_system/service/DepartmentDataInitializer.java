package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Department;
import com.example.springemployee_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DepartmentDataInitializer {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentDataInitializer(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void initializeData() {
        Department itDepartment = new Department();
        itDepartment.setDepartmentCode("HR");
        itDepartment.setDepartmentName("Human Resource");

        departmentService.saveDepartment(itDepartment);
    }
}
