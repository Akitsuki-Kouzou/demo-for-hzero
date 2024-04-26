package com.example.springdemo.repository;

import com.example.springdemo.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Optional;
import java.util.List;

@Repository
public class DepartmentRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Department> findAllDepartments() {
        String sql = "SELECT department_code, department_name FROM department";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Department(
                        resultSet.getString("department_code"),
                        resultSet.getString("department_name")
                ));
    }

    public Department saveDepartment(Department department) {
        String sql = "INSERT INTO department (department_code, department_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, department.getDepartmentCode(), department.getDepartmentName());
        return department;
    }

    public Optional<Department> findById(String department_code) {
        String sql = "SELECT * FROM department WHERE department_code = ?";
            Department department = jdbcTemplate.queryForObject(sql, new Object[]{department_code},
                    (rs, rowNum) -> new Department(
                            rs.getString("department_code"),
                            rs.getString("department_name")
                    ));
            return Optional.of(department);
    }
    public void updateDepartment(Department department) {
        String sql = "UPDATE department SET department_name = ? WHERE department_code = ?";
        jdbcTemplate.update(sql, department.getDepartmentName(), department.getDepartmentCode());
    }

    public void deleteDepartment(String department_code) {
        String sql = "DELETE FROM department WHERE department_code = ?";
        jdbcTemplate.update(sql, department_code);
    }
}
