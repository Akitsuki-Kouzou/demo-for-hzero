package com.example.springdemo.repository;

import com.example.springdemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Optional;
import java.util.List;

@Repository
public class EmployeeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAllEmployees() {
        String sql = "SELECT employee_code, employee_name, joining_date, department_code, position_code FROM employee"; // Adjust column names if needed
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Employee(
                        resultSet.getString("employee_code"),
                        resultSet.getString("employee_name"),
                        resultSet.getDate("joining_date"),
                        resultSet.getString("department_code"),
                        resultSet.getString("position_code")
                ));
    }

//    public Optional<Employee> findById(String employeeCode) {
//        String sql = "SELECT * FROM employee WHERE employee_code = ?";
//        try {
//            Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{employeeCode},
//                    (rs, rowNum) -> new Employee(
//                            rs.getString("employee_code"),
//                            rs.getString("employee_name"),
//                            rs.getDate("joining_date"),
//                            rs.getString("department_code"),
//                            rs.getString("position_code")
//                    ));
//            return Optional.of(employee);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
    public Employee saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                employee.getEmployee_code(),
                employee.getEmployee_name(),
                employee.getJoining_date(),
                employee.getDepartment_code(),
                employee.getPosition_code());
        return employee;
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET employee_name = ?, joining_date = ?, department_code = ?, position_code = ? WHERE employee_code = ?";
        jdbcTemplate.update(sql,
                employee.getEmployee_name(),
                employee.getJoining_date(),
                employee.getDepartment_code(),
                employee.getPosition_code(),
                employee.getEmployee_code());
    }

    public void deleteEmployee(String employeeCode) {
        String sql = "DELETE FROM employee WHERE employee_code = ?";
        jdbcTemplate.update(sql, employeeCode);
    }
}
