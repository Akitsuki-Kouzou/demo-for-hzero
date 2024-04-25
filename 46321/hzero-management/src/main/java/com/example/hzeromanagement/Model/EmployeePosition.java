package com.example.hzeromanagement.Model;

import com.example.hzeromanagement.Model.Department;

import javax.persistence.*;

@Entity
@Table(name = "emppos")
public class EmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posID;

    @Column(name = "pos_name", nullable = false)
    private String posName;

    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "depID", referencedColumnName = "depID")
    private Department department;

    // Getters and setters


    public Long getPosID() {
        return posID;
    }

    public void setPosID(Long posID) {
        this.posID = posID;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}