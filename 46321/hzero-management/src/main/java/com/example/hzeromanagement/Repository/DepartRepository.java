package com.example.hzeromanagement.Repository;

import com.example.hzeromanagement.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartRepository extends JpaRepository<Department, Long> {

    List<Department> findByDepNameLike(String depName);
}
