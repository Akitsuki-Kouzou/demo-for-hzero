package com.example.webapp.employee;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = false, unique = true, length = 45)
    private String employeeCode;

    @Column(nullable = false, unique = true, length = 45)
    private String employeeName;

    @Column(nullable = false, unique = true, length = 45)
    private String joiningDate;

    @Column(nullable = false, unique = true, length = 45)
    private String employeeDepartment;

    @Column(nullable = false, unique = true, length = 45)
    private String employeePosition;


    @Override
    public String toString() {
        return "Employee{" +
                "employeeCode=" + getEmployeeCode() +
                ", employeeName='" + getEmployeeName() + '\'' +
                ", joiningDate='" + getJoiningDate() + '\'' +
                ", employeeDepartment='" + getEmployeeDepartment() + '\'' +
                ", employeePosition='" + getEmployeePosition() + '\'' +
                '}';
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }
}
