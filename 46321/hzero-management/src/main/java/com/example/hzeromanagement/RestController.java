package com.example.hzeromanagement;

import com.example.hzeromanagement.Model.Department;
import com.example.hzeromanagement.Model.Employee;
import com.example.hzeromanagement.Model.EmployeePosition;
import com.example.hzeromanagement.Service.DepartmentService;
import com.example.hzeromanagement.Service.EmployeePositionService;
import com.example.hzeromanagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/management")
public class RestController {
    @Autowired
    private DepartmentService depService;
    @Autowired
    private EmployeePositionService posService;
    @Autowired
    private EmployeeService empService;

//    Department Mapping
    @GetMapping("/department")
    public List<Department> getAllDep() {
        return depService.getAllItems();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepById(@PathVariable Long id) {
        Department item = depService.getItemById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/department/fuzzy")
    public List<Department> getDepFuzzy(@RequestParam String name){
        List<Department> item = depService.getFuzzySearchName(name);
        return item;
    }

    @PostMapping("/department")
    public ResponseEntity<Department> addDep(@RequestBody Department item) {
        Department newItem = depService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDep(@PathVariable Long id, @RequestBody Department item) {
        Department updatedItem = depService.updateItem(id, item);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> deleteDep(@PathVariable Long id) {
        depService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //    Position Mapping
    @GetMapping("/position")
    public List<EmployeePosition> getAllPos() {
        return posService.getAllPosition();
    }

    @GetMapping("/position/{id}")
    public ResponseEntity<EmployeePosition> getPosById(@PathVariable Long id) {
        EmployeePosition pos = posService.getItemById(id);
        if (pos != null) {
            return new ResponseEntity<>(pos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/position/fuzzy")
    public List<EmployeePosition> getPosFuzzy(@RequestParam String name){
        List<EmployeePosition> pos = posService.getFuzzySearchName(name);
        return pos;
    }

    @PostMapping("/position")
    public ResponseEntity<EmployeePosition> addPos(@RequestBody EmployeePosition item) {
        EmployeePosition newItem = posService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/position/{id}")
    public ResponseEntity<EmployeePosition> updatePos(@PathVariable Long id, @RequestBody EmployeePosition item) {
        EmployeePosition updatedItem = posService.updateItem(id, item);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/position/{id}")
    public ResponseEntity<Void> deletePos(@PathVariable Long id) {
        posService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //    Employee Mapping
    @GetMapping("/employee")
    public List<Employee> getAllEmp() {
        return empService.getAllItems();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable Long id) {
        Employee emp = empService.getItemById(id);
        if (emp != null) {
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/fuzzy")
    public List<Employee> getEmpFuzzy(@RequestParam String name){
        List<Employee> emp = empService.getFuzzySearchName(name);
        return emp;
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee item) {
        Employee newItem = empService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Long id, @RequestBody Employee item) {
        Employee updatedItem = empService.updateItem(id, item);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable Long id) {
        empService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
