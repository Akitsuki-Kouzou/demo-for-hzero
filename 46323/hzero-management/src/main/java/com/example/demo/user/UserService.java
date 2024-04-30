package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer employee_code) throws UserNoFoundException {
        Optional<User> result = repo.findById(employee_code);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNoFoundException("Could not find any users with ID " + employee_code);
    }

    public void delete(Integer employee_code) throws UserNoFoundException {
        Long count = repo.countByEmployeeCode(employee_code);
        if (count == null || count == 0){
            throw new UserNoFoundException("Could not find any users with ID " + employee_code);
        }
        repo.deleteById(employee_code);
    }
}
