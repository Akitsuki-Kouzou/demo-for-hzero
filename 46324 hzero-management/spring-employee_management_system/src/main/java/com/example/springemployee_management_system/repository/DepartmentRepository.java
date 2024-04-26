package com.example.springemployee_management_system.repository;

import com.example.springemployee_management_system.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findByDepartmentNameContaining(String name);
}
