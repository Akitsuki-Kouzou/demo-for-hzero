package com.example.springdemo.Controllers;

import com.example.springdemo.Entities.EmployeeEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.EmployeeModel;
import com.example.springdemo.Models.ResponseModel;
import com.example.springdemo.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    ResponseModel response = new ResponseModel();
    EmployeeEntity employee = new EmployeeEntity();
    List<EmployeeEntity> employees = new ArrayList<>();

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> addEmployee(@RequestBody EmployeeModel employeeModel){
        try {
            employee = employeeService.addEmployee(employeeModel);

            response.setMsg("Employee is successfully added");
            response.setData(employee);
            response.setTime(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(response);
        }
        catch (ClientException e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseModel> getAllEmployees(){
        try {
            employees = employeeService.getAllEmployees();

            response.setData(employees);
            response.setMsg("Employees fetched successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping(value = "/get/{code}")
    public ResponseEntity<ResponseModel> getEmployeeByCode(@PathVariable String code){
        try {
            employee = employeeService.getEmployeeByCode(code);

            response.setData(employee);
            response.setMsg("Employee with code : " + code + " is found");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity<ResponseModel> searchEmployee(@RequestBody EmployeeModel employeeModel){
        try {
            employees = employeeService.searchEmployees(employeeModel);

            response.setData(employees);
            response.setMsg("Employees fetched successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping(value="/update")
    public ResponseEntity<ResponseModel> updateEmployee(@RequestBody EmployeeModel employeeModel){
        try {
            employee = employeeService.updateEmployee(employeeModel);

            response.setData(employee);
            response.setMsg("Employee updated successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Employee with code : " + employeeModel.getEmployeeCode() + " is not found");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }

    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseModel> deleteEmployee(@RequestBody EmployeeModel employeeModel){
        try {
            employee = employeeService.deleteEmployee(employeeModel);

            response.setData(employee);
            response.setMsg("Employee deleted successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Employee with code : " + employeeModel.getEmployeeCode() + " is not found");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.badRequest().body(response);
        }
        catch (Exception e){
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }


}
