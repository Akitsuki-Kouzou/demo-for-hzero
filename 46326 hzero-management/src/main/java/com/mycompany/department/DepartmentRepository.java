package com.mycompany.department;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    public Long countById(Integer id);
}
