package com.example.webapp.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository repo;

    public List<Employee> listAll(){
        return (List<Employee>) repo.findAll();
    }

    public void save(Employee employee) {
        repo.save(employee);
    }

    public Employee get(Integer employeeId) throws EmployeeNotFoundException{
        Optional<Employee> result = repo.findById(employeeId);
        if(result.isPresent()){
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any employee with ID " + employeeId);
    }

    public void delete(Integer employeeId) throws EmployeeNotFoundException {
        Long count = repo.countByEmployeeId(employeeId);
        if(count == null || count == 0){
            throw new EmployeeNotFoundException("Could not find any employee with ID " + employeeId);
        }
        repo.deleteById(employeeId);
    }
}
