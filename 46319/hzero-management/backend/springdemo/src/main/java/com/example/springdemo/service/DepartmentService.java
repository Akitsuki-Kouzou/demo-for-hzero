package com.example.springdemo.service;

import com.example.springdemo.model.Department;
import com.example.springdemo.repository.DepartmentRepo;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAllDepartments();
    }

    public List<Department> searchByName(String name) {
        List<Department> allDepartments = departmentRepo.findAllDepartments(); // Replace with your fetch method
        List<Department> fuzzySearchResult = new ArrayList<>();

        JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();

        for (Department department : allDepartments) {
            double similarityScore = jaroWinkler.apply(department.getDepartmentName(), name); // Assuming you search on department name
            if (similarityScore >= 0.75) { // You can adjust this threshold
                fuzzySearchResult.add(department);
            }
        }

        return fuzzySearchResult;
    }

    public Department saveDepartment(Department department) {
        return departmentRepo.saveDepartment(department);
    }

//    public Department findDepartmentById(String departmentCode) {
//        Optional<Department> optionalDepartment = departmentRepo.findById(departmentCode);
//        return optionalDepartment.orElse(null); // Or any other handling if not found
//    }

    public Department updateDepartment(Department department) {
        departmentRepo.updateDepartment(department);
        return department;
    }

    public void deleteDepartment(String departmentCode) {
        departmentRepo.deleteDepartment(departmentCode);
    }
}
