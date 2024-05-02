package com.example.springdemo.Controllers;

import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Entities.PositionEntity;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.DepartmentModel;
import com.example.springdemo.Models.PositionModel;
import com.example.springdemo.Models.ResponseModel;
import com.example.springdemo.Services.DepartmentService;
import com.example.springdemo.Services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    ResponseModel response = new ResponseModel();

    PositionEntity position = new PositionEntity();
    List<PositionEntity> positions = new ArrayList<>();

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> addPosition(@RequestBody PositionModel positionModel){
        try {

            position = positionService.addPosition(positionModel);

            response.setMsg("Position added successfully");
            response.setData(position);
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
    public ResponseEntity<ResponseModel> getAllPositions(){
        try {
            positions = positionService.getAllPosition();

            response.setData(positions);
            response.setMsg("Positions fetched successfully");
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
    public ResponseEntity<ResponseModel> getPositionByCode(@PathVariable String code){
        try {
            position = positionService.getPositionByCode(code);

            response.setData(position);
            response.setMsg("Position with code : " + code + " is found");
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
    public ResponseEntity<ResponseModel> searchPosition(@RequestBody PositionModel positionModel){
        try {
            positions = positionService.searchPosition(positionModel);

            response.setData(positions);
            response.setMsg("Positions fetched successfully");
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
    public ResponseEntity<ResponseModel> updatePosition(@RequestBody PositionModel positionModel){
        try {
            position = positionService.updatePosition(positionModel);

            response.setData(position);
            response.setMsg("Position updated successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Position with code : " + positionModel.getPositionCode() + " is not found");
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
    public ResponseEntity<ResponseModel> deletePosition(@RequestBody PositionModel positionModel){
        try {
            position = positionService.deletePosition(positionModel);

            response.setData(position);
            response.setMsg("Position deleted successfully");
            response.setTime(new Timestamp(System.currentTimeMillis()));

            return ResponseEntity.ok(response);
        }
        catch (NotFoundException e){
            response.setMsg("Position with code : " + positionModel.getPositionCode() + " is not found");
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
