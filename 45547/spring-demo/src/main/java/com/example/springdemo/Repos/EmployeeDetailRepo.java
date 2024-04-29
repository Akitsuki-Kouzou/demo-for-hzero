package com.example.springdemo.Repos;

import com.example.springdemo.Entities.EmployeeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetailEntity, String>, JpaSpecificationExecutor<EmployeeDetailEntity> {

    @Query(value = "SELECT employee_code, "
            + " employee_name, "
            + " join_date, "
            + " department_code, "
            + " department_name, "
            + " position_code, "
            + " position_name "
            + " FROM ms_employee "
            + " INNER JOIN ms_department ON "
            + " ms_employee.department_code = ms_department.department_code "
            + " INNER JOIN ms_position ON "
            + " ms_department.department_code = ms_position.department_code "
            , nativeQuery = true
    )
    List<EmployeeDetailEntity> getAllEmployeeDetail();

    @Query(value = "SELECT employee_code, "
            + " employee_name, "
            + " join_date, "
            + " department_name, "
            + " position_name "
            + " FROM ms_employee "
            + " INNER JOIN ms_department ON "
            + " ms_employee.department_code = ms_department.department_code "
            + " INNER JOIN ms_position ON "
            + " ms_department.department_code = ms_position.department_code "
            + " WHERE employee_code = :employeeCode "
            , nativeQuery = true
    )
    EmployeeDetailEntity getEmployeeDetailByCode(@Param("employeeCode") String employeeCode );

    @Query(value = "SELECT employee_code, "
            + " employee_name, "
            + " join_date, "
            + " department_name, "
            + " position_name "
            + " FROM ms_employee "
            + " INNER JOIN ms_department ON "
            + " ms_employee.department_code = ms_department.department_code "
            + " INNER JOIN ms_position ON "
            + " ms_department.department_code = ms_position.department_code "
            + " WHERE LOWER(employee_code) LIKE %:employeeCode% "
            + " OR LOWER(employee_name) LIKE %:employeeName% "
            + " OR LOWER(department_code) LIKE %:departmentCode% "
            + " OR LOWER(department_name) LIKE %:departmentName% "
            + " OR LOWER(position_code) LIKE %:positionCode% "
            + " OR LOWER(position_name) LIKE %:positionName% "
            , nativeQuery = true
    )
    List<EmployeeDetailEntity> searchEmployeeDetail(
            @Param("employeeCode") String employeeCode,
            @Param("employeeName") String employeeName,
            @Param("departmentCode") String departmentCode,
            @Param("departmentName") String departmentName,
            @Param("positionCode") String positionCode,
            @Param("positionName") String positionName
    );

}
