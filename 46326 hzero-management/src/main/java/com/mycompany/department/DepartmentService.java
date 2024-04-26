package com.mycompany.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired private DepartmentRepository repo;

    public List<Department> listAll() {
        return (List<Department>) repo.findAll();
    }

    public void save(Department department) {
        repo.save(department);
    }

    public Department get(Integer id) throws DepartmentNotFoundException {
        Optional<Department> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new DepartmentNotFoundException("Could not find any department with ID " + id);
    }

    public void delete(Integer id) throws DepartmentNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new DepartmentNotFoundException("Could not find any departments with ID " + id);
        }
        repo.deleteById(id);
    }
}
