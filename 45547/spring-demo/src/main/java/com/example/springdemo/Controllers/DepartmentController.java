package com.example.springdemo.Controllers;

import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.DepartmentModel;
import com.example.springdemo.Models.EmployeeModel;
import com.example.springdemo.Models.ResponseModel;
import com.example.springdemo.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    ResponseModel response = new ResponseModel();

    DepartmentEntity department = new DepartmentEntity();
    List<DepartmentEntity> departments = new ArrayList<>();

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> addDepartment(@RequestBody DepartmentModel departmentModel){
        try {

            DepartmentEntity department = new DepartmentEntity();

            department = departmentService.addDepartment(departmentModel);

            response.setMsg("Department added successfully");
            response.setData(department);
            response.setTime(new Timestamp((System.currentTimeMillis())));

            return ResponseEntity.ok(response);
        }
        catch (Exception e){

            response.setMsg(e.getMessage());
            response.setTime(new Timestamp((System.currentTimeMillis())));

            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseModel> getAllDepartments(){
        try {
            departments = departmentService.getAllDepartment();

            response.setData(departments);
            response.setMsg("Departemns fetched successfully");
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
    public ResponseEntity<ResponseModel> getDepartmentByCode(@PathVariable String code){
        try {
            department = departmentService.getDepartmentByCode(code);

            response.setData(department);
            response.setMsg("Department with code : " + code + " is found");
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
    public ResponseEntity<ResponseModel> searchDepartment(@RequestBody DepartmentModel departmentModel){
        try {
            departments = departmentService.searchDepartment(departmentModel);

            response.setData(departments);
            response.setMsg("Departments fetched successfully");
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
    public ResponseEntity<ResponseModel> updateDepartment(@RequestBody DepartmentModel departmentModel){
        try {
            department = departmentService.updateDepartment(departmentModel);

            response.setData(department);
            response.setMsg("Department updated successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Department with code : " + departmentModel.getDepartmentCode() + " is not found");
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
    public ResponseEntity<ResponseModel> deleteDepartment(@RequestBody DepartmentModel departmentModel){
        try {
            department = departmentService.deleteDepartment(departmentModel);

            response.setData(department);
            response.setMsg("Department deleted successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Department with code : " + departmentModel.getDepartmentCode() + " is not found");
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
