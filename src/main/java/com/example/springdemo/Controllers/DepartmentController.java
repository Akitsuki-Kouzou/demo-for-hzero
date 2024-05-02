package com.example.springdemo.Controllers;

import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Mappers.DepartmentMapper;
import com.example.springdemo.Models.DepartmentModel;
import com.example.springdemo.Models.EmployeeModel;
import com.example.springdemo.Models.ResponseModel;
import com.example.springdemo.Services.DepartmentService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllDepartment(){
        try {
            departments = departmentService.getAllDepartment();

            response.setData(departments);
            response.setMsg("Departments fetched successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setData(null);
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
            response.setMsg("Department with code : "+ code + " is fetched successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel> createEmployee(@RequestBody DepartmentModel departmentModel){
        try {

            departmentService.createDepartment(departmentModel);

            response.setData(departmentModel);
            response.setMsg("Department is successfully created");
            response.setTime(new Timestamp(System.currentTimeMillis()));
            return ResponseEntity.ok(response);
        }
        catch (ClientException e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> updateDepartment(@RequestBody DepartmentModel departmentModel){
        try {
            departmentService.updateDepartment(departmentModel);

            response.setData(departmentModel);
            response.setMsg("Department with code :" + departmentModel.getDepartmentCode() + " is updated successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(404).body(response);
        }
        catch (Exception e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }

    @DeleteMapping(value = "/delete/{departmentCode}")
    public ResponseEntity<ResponseModel> deleteDepartment(@PathVariable String departmentCode){
        try {
            departmentService.deleteDepartment(departmentCode);

            response.setData(null);
            response.setMsg("Department with code : " + departmentCode + " is deleted successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(404).body(response);
        }
        catch (Exception e){
            response.setData(null);
            response.setMsg(e.getMessage());
            response.setTime(new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();

            return ResponseEntity.status(500).body(response);
        }
    }
}
