package com.example.demo.department;

public class DepartmentNoFoundException extends Throwable{
    public DepartmentNoFoundException(String message){
        super(message);
    }
}
