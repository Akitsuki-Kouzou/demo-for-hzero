package com.example.springdemo.model;

public class Department {
    private String department_code;
    private String department_name;

    // Constructors (All args, no-arg)
    public Department(String department_code, String department_name) {
        this.department_code = department_code;
        this.department_name = department_name;
    }

    public Department() {} // No-argument constructor

    // Getters and Setters
    public String getDepartmentCode() {
        return department_code;
    }

    public void setDepartmentCode(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartmentName() {
        return department_name;
    }

    public void setDepartmentName(String department_name) {
        this.department_name = department_name;
    }
}
