package com.example.webapp.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired private DepartmentRepository repo;

    public List<Department> listAll(){
        return (List<Department>) repo.findAll();
    }

    public void save(Department department) {
        repo.save(department);
    }

    public Department get(Integer departmentId) throws DepartmentNotFoundException {
        Optional<Department> result = repo.findById(departmentId);
        if(result.isPresent()){
            return result.get();
        }
        throw new DepartmentNotFoundException("Could not find any department with ID " + departmentId);
    }

    public void delete(Integer departmentId) throws DepartmentNotFoundException {
        Long count = repo.countByDepartmentId(departmentId);
        if(count == null || count == 0){
            throw new DepartmentNotFoundException("Could not find any department with ID " + departmentId);
        }
        repo.deleteById(departmentId);
    }
}
