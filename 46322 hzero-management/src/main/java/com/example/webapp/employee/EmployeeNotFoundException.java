package com.example.webapp.employee;

public class EmployeeNotFoundException extends Throwable {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
