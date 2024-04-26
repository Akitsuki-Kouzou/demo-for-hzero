package com.example.webapp;

import com.example.webapp.employee.Employee;
import com.example.webapp.employee.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repo;

    @Test
    public void testAddNew() {
        Employee employeeDetails = new Employee();
        employeeDetails.setEmployeeCode("50");
        employeeDetails.setEmployeeName("Techsul 345");
        employeeDetails.setEmployeeDepartment("Temporary");
        employeeDetails.setJoiningDate("20-01-2030");
        employeeDetails.setEmployeePosition("Temporary");

        Employee savedEmployee = repo.save(employeeDetails);
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
