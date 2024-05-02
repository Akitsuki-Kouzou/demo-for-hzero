package com.example.springdemo.Validations;

import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;

public class Validator {
    public void checkNullObject(Object o) throws NotFoundException {
        if(o==null) throw new NotFoundException("Object not found!");
    }

    public void checkMaxChar(String s) throws ClientException {
        if(s.length()>10) throw new ClientException("Cannot be more than 10 characters!");
    }
}
