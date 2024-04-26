package com.example.webapp.department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    public long countByDepartmentId(Integer departmentId);
    List<Department> findByDepartmentId(Integer departmentId);
}
