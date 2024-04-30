package com.example.springdemo.model;

public class Position {
    private String position_code;
    private String position_name;
    private String department_code;

    public Position(String position_code, String position_name, String department_code) {
        this.position_code = position_code;
        this.position_name = position_name;
        this.department_code = department_code;
    }

    public Position() {} // No-argument constructor

    // Getters and Setters
    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }
}
