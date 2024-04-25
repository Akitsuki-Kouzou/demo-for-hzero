package com.example.demo;

import com.example.demo.department.Department;
import com.example.demo.department.DepartmentRepository;
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
    @Autowired private DepartmentRepository repo;

    @Test
    public void testAddNew(){
        Department department = new Department();
        department.setDepartmentCode(1);
        department.setDepartmentName("Tech Dept 1");

        Department savedDepartment = repo.save(department);

        Assertions.assertThat(savedDepartment).isNotNull();
        Assertions.assertThat(savedDepartment.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Department> Departments = repo.findAll();
        Assertions.assertThat(Departments).hasSizeGreaterThan(0);

        for (Department department : Departments) {
            System.out.println(department);
        }
    }

    @Test
    public void testUpdate(){
        Integer departmentId= 1;
        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Department department = optionalDepartment.get();
        department.setDepartmentName("Tech Dept 2");
        repo.save(department);

        Department updateDepartment = repo.findById(departmentId).get();

    }

    @Test
    public void testGet(){
        Integer departmentId = 1;
        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Assertions.assertThat(optionalDepartment).isPresent();
        System.out.println(optionalDepartment.get());

    }

    @Test
    public void testDelete(){
        Integer departmentId = 1;
        repo.deleteById(departmentId);

        Optional<Department> optionalDepartment = repo.findById(departmentId);
        Assertions.assertThat(optionalDepartment).isNotPresent();
    }

}
