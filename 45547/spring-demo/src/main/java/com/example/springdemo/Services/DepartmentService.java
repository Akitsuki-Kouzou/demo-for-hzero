package com.example.springdemo.Services;

import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.DepartmentModel;
import com.example.springdemo.Repos.DepartmentRepo;
import com.example.springdemo.Validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    Validator validator = new Validator();

    public DepartmentEntity addDepartment(DepartmentModel departmentModel) throws ClientException {
        Long count = departmentRepo.countById(departmentModel.getDepartmentCode());
        if(count > 0 ) throw new ClientException("Department code already exists!");

        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentCode(departmentModel.getDepartmentCode());
        department.setDepartmentName(departmentModel.getDepartmentName());

        return departmentRepo.save(department);
    }

    public List<DepartmentEntity> getAllDepartment(){
        List<DepartmentEntity> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(departments::add);

        return departments;
    }

    public DepartmentEntity getDepartmentByCode(String code) throws NotFoundException{
        DepartmentEntity department = departmentRepo.findById(code).orElse(null);
        if(department == null){
            throw new NotFoundException("Department not found!");
        }

        return department;
    }

    public List<DepartmentEntity> searchDepartment(DepartmentModel departmentModel){
        List<DepartmentEntity> departments = new ArrayList<>();
        departments = departmentRepo.searchDepartment(departmentModel.getDepartmentCode().toLowerCase(),
                departmentModel.getDepartmentName().toLowerCase());

        return departments;
    }

    public DepartmentEntity updateDepartment(DepartmentModel departmentModel) throws NotFoundException{
        DepartmentEntity department = new DepartmentEntity();

        if(!departmentRepo.existsById(departmentModel.getDepartmentCode())){
            throw new NotFoundException("Cannot found department with code : "+ departmentModel.getDepartmentCode());
        }
        department.setDepartmentCode(departmentModel.getDepartmentCode());

        if(departmentModel.getDepartmentName() != null){
            department.setDepartmentName(departmentModel.getDepartmentName());
        }

        return departmentRepo.save(department);
    }

    public DepartmentEntity deleteDepartment(DepartmentModel departmentModel) throws NotFoundException{
        DepartmentEntity department = new DepartmentEntity();

        if(!departmentRepo.existsById(departmentModel.getDepartmentCode())){
            throw new NotFoundException("Cannot found department with code : " + departmentModel.getDepartmentCode());
        }

        department = departmentRepo.findById(departmentModel.getDepartmentCode()).orElse(null);
        departmentRepo.deleteById(departmentModel.getDepartmentCode());

        return department;
    }
}
