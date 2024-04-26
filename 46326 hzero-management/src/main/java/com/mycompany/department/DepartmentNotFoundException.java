package com.mycompany.department;

public class DepartmentNotFoundException extends Throwable {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
