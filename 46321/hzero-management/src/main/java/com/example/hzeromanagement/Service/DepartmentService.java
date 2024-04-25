package com.example.hzeromanagement.Service;

import com.example.hzeromanagement.Repository.DepartRepository;
import com.example.hzeromanagement.Model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartRepository departRepository;

    public List<Department> getAllItems() {
        return departRepository.findAll();
    }
    public List<Department> getFuzzySearchName(String name){
        return departRepository.findByDepNameLike("%"+name+"%");
    }

    public Department getItemById(Long id) {
        return departRepository.findById(id).orElse(null);
    }

    public Department addItem(Department item) {
        return departRepository.save(item);
    }

    public Department updateItem(Long id, Department newItem) {
        Department existingItem = departRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setDepName(newItem.getDepName());
            return departRepository.save(existingItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        departRepository.deleteById(id);
    }
}
