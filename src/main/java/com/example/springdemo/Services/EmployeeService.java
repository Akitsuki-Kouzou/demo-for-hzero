package com.example.springdemo.Services;

import com.example.springdemo.Entities.EmployeeEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.EmployeeModel;
import com.example.springdemo.Repos.EmployeeRepo;
import com.example.springdemo.Validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    Validator validator = new Validator();

    public EmployeeEntity addEmployee(EmployeeModel employeeModel) throws ClientException{
        Long count = employeeRepo.countById(employeeModel.getEmployeeCode());
        if(count > 0) throw new ClientException("Employee Code already exist!");

        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeCode(employeeModel.getEmployeeCode());
        employee.setEmployeeName(employeeModel.getEmployeeName());
        employee.setJoinDate(employeeModel.getJoinDate());
        employee.setPositionCode(employeeModel.getPositionCode());
        employee.setDepartmentCode(employeeModel.getDepartmentCode());

        return employeeRepo.save(employee);
    }

    public List<EmployeeEntity> getAllEmployees(){
        List<EmployeeEntity> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(employees::add);

        return employees;
    }

    public EmployeeEntity getEmployeeByCode(String code) throws NotFoundException{
        EmployeeEntity employee = employeeRepo.findById(code).orElse(null);
        if(employee == null) throw new NotFoundException("Employee Not Found !");

        return employee;
    }

    public List<EmployeeEntity> searchEmployees(EmployeeModel employeeModel){
        List<EmployeeEntity> employees = new ArrayList<>();
        employees = employeeRepo.searchEmployee(employeeModel.getEmployeeCode().toLowerCase(), employeeModel.getEmployeeName().toLowerCase());

        return employees;
    }

    public EmployeeEntity updateEmployee(EmployeeModel employeeModel) throws NotFoundException{
        EmployeeEntity employee = new EmployeeEntity();

        if(!employeeRepo.existsById(employeeModel.getEmployeeCode())){
            throw new NotFoundException("Cannot found employee with code : "+ employeeModel.getEmployeeCode());
        }
        employee.setEmployeeCode(employeeModel.getEmployeeCode());

        if(employeeModel.getEmployeeName() != null){
            employee.setEmployeeName(employeeModel.getEmployeeName());
        }

        if(employeeModel.getJoinDate() != null){
            employee.setJoinDate(employeeModel.getJoinDate());
        }

        if(employeeModel.getDepartmentCode() != null){
            employee.setDepartmentCode("employeeModel.getDepartmentCode()");
        }

        if(employeeModel.getPositionCode() != null){
            employee.setPositionCode("employeeModel.getPositionCode()");
        }

        System.out.println(employeeModel.getJoinDate() + employeeModel.getDepartmentCode() + employeeModel.getPositionCode() );
        return employeeRepo.save(employee);
    }

    public EmployeeEntity deleteEmployee(EmployeeModel employeeModel) throws NotFoundException {
        EmployeeEntity employee = new EmployeeEntity();

        if(!employeeRepo.existsById(employeeModel.getEmployeeCode())){
            throw new NotFoundException("Cannot find employee with code : "+ employeeModel.getEmployeeCode());
        }

        employee = employeeRepo.findById(employeeModel.getEmployeeCode()).orElse(null);
        employeeRepo.deleteById(employeeModel.getEmployeeCode());

        return employee;
    }
}
