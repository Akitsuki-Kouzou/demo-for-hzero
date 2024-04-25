package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer> {
    public Long countByEmployeeCode(Integer employee_code);
}
