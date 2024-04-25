package com.example.demo.user;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeCode;

    @Column(length = 45, nullable = false)
    private String employeeName;

    @Column(length = 10, nullable = false)
    private String joiningDate;

    @Column(length = 30, nullable = false)
    private String department;

    @Column(length = 45, nullable = false)
    private String position;

    public Integer getEmployee_code() {
        return employeeCode;
    }

    public void setEmployee_code(Integer employee_code) {
        this.employeeCode = employee_code;
    }

    public String getEmployee_name() {
        return employeeName;
    }

    public void setEmployee_name(String employee_name) {
        this.employeeName = employee_name;
    }

    public String getJoining_date() {
        return joiningDate;
    }

    public void setJoining_date(String joining_date) {
        this.joiningDate = joining_date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "employeeCode=" + employeeCode +
                ", employeeName='" + employeeName + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
