package com.example.demo.position;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionCode;

    @Column(length = 25, nullable = false)
    private String positionName;

    @Column(length = 35, nullable = false)
    private String department;

    public Integer getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Integer positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "positionCode=" + positionCode +
                ", positionName='" + positionName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
