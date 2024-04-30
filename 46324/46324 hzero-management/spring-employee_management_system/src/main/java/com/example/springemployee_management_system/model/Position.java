package com.example.springemployee_management_system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_position")
public class Position {
    @Id
    @GeneratedValue
    private Integer positionId;
    private String positionCode;
    private String positionName;

    public String getPositionDepartmentCode() {
        return positionDepartmentCode;
    }

    public void setPositionDepartmentCode(String positionDepartmentCode) {
        this.positionDepartmentCode = positionDepartmentCode;
    }

    private String positionDepartmentCode;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
