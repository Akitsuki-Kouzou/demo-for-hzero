package com.example.demo.department;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository <Department, Integer> {
    public Long countByDepartmentId(Integer departmentId);
}
