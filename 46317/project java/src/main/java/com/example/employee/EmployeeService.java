package com.example.employee;

//import com.mycompany.position.Position;
//import com.mycompany.position.PositionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired private EmployeeRepository repo;

    public List<Employee> listAll() {
        return (List<Employee>) repo.findAll();
    }

    public void save(Employee employee) {
        repo.save(employee);
    }

    public Employee get(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EmployeeNotFoundException("Could not find any Employee with ID " + id);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EmployeeNotFoundException("Could not find any Employee with ID " + id);
        }
        repo.deleteById(id);
    }
}

