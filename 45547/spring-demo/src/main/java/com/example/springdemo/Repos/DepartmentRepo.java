package com.example.springdemo.Repos;

import com.example.springdemo.Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, String>, JpaSpecificationExecutor {

    @Query(value =
            "SELECT COUNT(*) FROM ms_department WHERE LOWER(department_code) = LOWER(:departmentCode)",
            nativeQuery = true)
    long countById(@Param("departmentCode") String deparmentCode);

    @Query(value = "SELECT * FROM ms_department "
    + " WHERE LOWER(department_code) LIKE %:departmentCode% "
    + " AND LOWER(department_name) LIKE %:departmentName% ", nativeQuery = true)
    List<DepartmentEntity> searchDepartment(@Param("departmentCode") String departmentCode,
                                            @Param("departmentName") String departmentName);
}
