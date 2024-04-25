package com.example.hzeromanagement.Service;

import com.example.hzeromanagement.Repository.EmployeeRepository;
import com.example.hzeromanagement.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public List<Employee> getAllItems() {
        return empRepository.findAll();
    }
    public List<Employee> getFuzzySearchName(String name){
        return empRepository.findByEmployeeNameLike("%"+name+"%");
    }

    public Employee getItemById(Long id) {
        return empRepository.findById(id).orElse(null);
    }

    public Employee addItem(Employee item) {
        return empRepository.save(item);
    }

    public Employee updateItem(Long id, Employee newItem) {
        Employee existingItem = empRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setEmployeeName(newItem.getEmployeeName());
            existingItem.setDepartment(newItem.getDepartment());
            existingItem.setJoinDate(newItem.getJoinDate());
            existingItem.setPosition(newItem.getPosition());
            return empRepository.save(existingItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        empRepository.deleteById(id);
    }
}
