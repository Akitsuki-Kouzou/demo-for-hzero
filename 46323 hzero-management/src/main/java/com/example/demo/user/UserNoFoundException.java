package com.example.demo.user;

public class UserNoFoundException extends Throwable {
    public UserNoFoundException(String message) {
        super(message);
    }
}
