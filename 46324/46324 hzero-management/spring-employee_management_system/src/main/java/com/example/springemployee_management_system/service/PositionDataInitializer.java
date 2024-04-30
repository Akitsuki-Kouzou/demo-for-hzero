package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Employee;
import com.example.springemployee_management_system.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PositionDataInitializer {
    private final PositionService positionService;

    @Autowired
    public PositionDataInitializer(PositionService positionService, EmployeeService employeeService) {
        this.positionService = positionService;
    }

    public void initializeData() {
        Position position1 = new Position();
        position1.setPositionCode("POS001");
        position1.setPositionName("Software Developer");
        position1.setPositionDepartmentCode("IT");

        Position position2 = new Position();
        position2.setPositionCode("PO2001");
        position2.setPositionName("HR Manager");
        position2.setPositionDepartmentCode("HR");

        positionService.savePosition(position1);
        positionService.savePosition(position2);
    }
}
