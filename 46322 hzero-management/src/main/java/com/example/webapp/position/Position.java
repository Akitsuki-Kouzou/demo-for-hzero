package com.example.webapp.position;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer positionId;

    @Column(nullable = false, unique = true, length = 45)
    private String positionCode;

    @Column(nullable = false, unique = true, length = 45)
    private String positionName;

    @Column(nullable = false, unique = true, length = 45)
    private String positionDepartment;

    @Override
    public String toString() {
        return "Position{" +
                "positionCode=" + getPositionCode() +
                ", positionName='" + getPositionName() + '\'' +
                ", positionDepartment='" + getPositionDepartment() + '\'' +
                '}';
    }

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

    public String getPositionDepartment() {
        return positionDepartment;
    }

    public void setPositionDepartment(String positionDepartment) {
        this.positionDepartment = positionDepartment;
    }
}
