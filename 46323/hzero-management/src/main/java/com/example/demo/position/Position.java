package com.example.demo.position;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionId;

    @Column(length = 25, nullable = false)
    private String positionCode;

    @Column(length = 35, nullable = false)
    private String positionName;

    @Column(length = 35, nullable = false)
    private String department;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer position_id) {
        this.positionId = position_id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String position_code) {
        this.positionCode = position_code;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String position_name) {
        this.positionName = position_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionCode='" + positionCode + '\'' +
                ", positionName='" + positionName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
