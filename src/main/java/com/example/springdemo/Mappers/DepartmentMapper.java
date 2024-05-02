package com.example.springdemo.Mappers;

import com.example.springdemo.Entities.DepartmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentEntity getDepartmentByCode(@Param("departmentCode") String departmentCode);
    long countById(@Param("departmentCode") String deparmentCode);

    List<DepartmentEntity> searchDepartment(String departmentCode, String departmentName);

    List<DepartmentEntity> getAllDepartment();

    void createDepartment(DepartmentEntity departmentEntity);

    void updateDepartment(DepartmentEntity departmentEntity);

    void deleteDepartment(String departmentCode);



}
