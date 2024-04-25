package com.example.hzeromanagement.Service;

import com.example.hzeromanagement.Model.EmployeePosition;
import com.example.hzeromanagement.Repository.PosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeePositionService {

    @Autowired
    private PosRepository posRepository;

    public List<EmployeePosition> getAllPosition() {
        return posRepository.findAll();
    }
    public List<EmployeePosition> getFuzzySearchName(String name){
        return posRepository.findByPosNameLike("%"+name+"%");
    }

    public EmployeePosition getItemById(Long id) {
        return posRepository.findById(id).orElse(null);
    }

    public EmployeePosition addItem(EmployeePosition item) {
        return posRepository.save(item);
    }

    public EmployeePosition updateItem(Long id, EmployeePosition newItem) {
        EmployeePosition existingItem = posRepository.findById(id).orElse(null);
        if (existingItem != null) {
            existingItem.setPosName(newItem.getPosName());
            existingItem.setDepartment(newItem.getDepartment());
            return posRepository.save(existingItem);
        }
        return null;
    }

    public void deleteItem(Long id) {
        posRepository.deleteById(id);
    }
}
