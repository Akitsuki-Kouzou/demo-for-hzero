package com.example.position;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 8)
    private String positionCode;

    @Column(nullable = false, length = 25)
    private String positionDepartment;

    @Column(nullable = false, unique = true, length = 8)
    private String positionName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionDepartment() {
        return positionDepartment;
    }

    public void setGetPositionDepartment(String positionDepartment) {
        this.positionDepartment = positionDepartment;
    }

    public String getpositionName() {
        return positionName;
    }

    public void setPositionName(String PositionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionCode='" + positionCode + '\'' +
                ", getPositionDepartment='" + positionDepartment + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}

