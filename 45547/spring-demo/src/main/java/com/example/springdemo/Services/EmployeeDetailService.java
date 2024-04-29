package com.example.springdemo.Services;

import com.example.springdemo.Entities.EmployeeDetailEntity;
import com.example.springdemo.Entities.EmployeeEntity;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.EmployeeDetailModel;
import com.example.springdemo.Repos.EmployeeDetailRepo;
import com.example.springdemo.Repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDetailService {
    @Autowired
    private EmployeeDetailRepo employeeDetailRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<EmployeeDetailEntity> getAllEmployeeDetail(){
        List<EmployeeDetailEntity> employeeDetails = new ArrayList<>();
        employeeDetailRepo.getAllEmployeeDetail().forEach(employeeDetails::add);

        return employeeDetails;
    }

    public EmployeeDetailEntity getEmployeeDetailByCode(String code) throws NotFoundException {
        EmployeeEntity employee = employeeRepo.findById(code).orElse(null);
        if(employee == null) throw new NotFoundException("Employee Not Found !");

        EmployeeDetailEntity employeeDetail = employeeDetailRepo.getEmployeeDetailByCode(code);

        return employeeDetail;
    }

    public List<EmployeeDetailEntity> searchEmployeeDetail(EmployeeDetailModel employeeDetailModel){
        List<EmployeeDetailEntity> employeeDetails = new ArrayList<>();
        employeeDetailRepo.searchEmployeeDetail(
                employeeDetailModel.getEmployeeCode(),
                employeeDetailModel.getEmployeeName(),
                employeeDetailModel.getDepartmentCode(),
                employeeDetailModel.getDepartmentName(),
                employeeDetailModel.getPositionCode(),
                employeeDetailModel.getPositionName()
                ).forEach(employeeDetails::add);

        return employeeDetails;
    }

}
