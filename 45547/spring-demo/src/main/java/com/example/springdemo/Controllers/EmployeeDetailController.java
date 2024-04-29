package com.example.springdemo.Controllers;

import com.example.springdemo.Entities.EmployeeDetailEntity;
import com.example.springdemo.Entities.PositionEntity;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.EmployeeDetailModel;
import com.example.springdemo.Models.PositionModel;
import com.example.springdemo.Models.ResponseModel;
import com.example.springdemo.Services.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee/detail")
public class EmployeeDetailController {

    @Autowired
    private EmployeeDetailService employeeDetailService;

    ResponseModel response = new ResponseModel();

    EmployeeDetailEntity employeeDetail = new EmployeeDetailEntity();
    List<EmployeeDetailEntity> employeeDetails = new ArrayList<>();

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseModel> getAllEmployeeDetails(){
        try {
            employeeDetails = employeeDetailService.getAllEmployeeDetail();

            response.setData(employeeDetails);
            response.setMsg("Employee Details fetched successfully");
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
    public ResponseEntity<ResponseModel> getEmployeeDetailByCode(@PathVariable String code){
        try {
            employeeDetail = employeeDetailService.getEmployeeDetailByCode(code);

            response.setData(employeeDetail);
            response.setMsg("Employee details with code : " + code + " is found");
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
    public ResponseEntity<ResponseModel> searchEmployeeDetails(@RequestBody EmployeeDetailModel employeeDetailModel){
        try {
            employeeDetails = employeeDetailService.searchEmployeeDetail(employeeDetailModel);

            response.setData(employeeDetails);
            response.setMsg("Employee details fetched successfully");
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
}
