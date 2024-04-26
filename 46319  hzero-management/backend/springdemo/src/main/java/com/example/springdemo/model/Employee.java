package com.example.springdemo.model;

import java.util.Date;

public class Employee {
    private String employee_code;
    private String employee_name;
    private Date joining_date;
    private String department_code;
    private String position_code;

    // Constructors (All args, no-arg)
    public Employee(String employee_code, String employee_name, Date joining_date, String department_code, String position_code) {
        this.employee_code = employee_code;
        this.employee_name = employee_name;
        this.joining_date = joining_date;
        this.department_code = department_code;
        this.position_code = position_code;
    }

    public Employee() {} // No-argument constructor

    // Getters and Setters
    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }
}
