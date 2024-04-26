package com.example.webapp;

import com.example.webapp.department.Department;
import com.example.webapp.department.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository repo;

    @Test
    public void testAddNew() {
        Department departmentDetails = new Department();
        departmentDetails.setDepartmentCode("25");
        departmentDetails.setDepartmentName("Techsul 3");

        Department savedDepartment = repo.save(departmentDetails);
        Assertions.assertThat(savedDepartment).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Department> departments = repo.findAll();

        for (Department department : departments){
            System.out.println(department);
        }
    }

    @Test
    public void testUpdate(){
        Integer departmentId = 21;
        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Department departmentDetails = optionalDepartment.get();
        departmentDetails.setDepartmentCode("24");
        repo.save(departmentDetails);

        Department updatedDepartment = repo.findById(departmentId).get();
    }

    @Test
    public void testGet(){
        Integer departmentId = 2;
        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Department departmentDetails = optionalDepartment.get();
        Assertions.assertThat(optionalDepartment).isPresent();
        System.out.println(optionalDepartment.get());
    }

    @Test
    public void testDelete(){
        Integer departmentId = 2;
        repo.deleteById(departmentId);

        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Assertions.assertThat(optionalDepartment).isNotPresent();
    }

}
