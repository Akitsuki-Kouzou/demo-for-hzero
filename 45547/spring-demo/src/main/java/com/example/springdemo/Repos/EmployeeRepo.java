package com.example.springdemo.Repos;

import com.example.springdemo.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, String>, JpaSpecificationExecutor<EmployeeEntity> {

    @Query(value =
            "SELECT COUNT(*) FROM ms_employee WHERE LOWER(employee_code) = LOWER(:employeeCode)",
            nativeQuery = true)
    long countById(@Param("employeeCode") String employeeCode);

    @Query(value = "SELECT * FROM ms_employee " +
            " WHERE LOWER(employee_code) LIKE %:employeeCode% " +
            " AND LOWER(employee_name) LIKE %:employeeName% ", nativeQuery = true
    ) List<EmployeeEntity> searchEmployee(@Param("employeeCode") String employeeCode,
                                          @Param("employeeName") String employeeName);
}
