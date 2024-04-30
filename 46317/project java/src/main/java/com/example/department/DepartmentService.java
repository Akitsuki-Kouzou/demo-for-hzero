package com.example.department;

//import com.mycompany.position.Position;
//import com.mycompany.position.PositionNotFoundException;
import com.example.department.Department;
import com.example.department.DepartmentNotFoundException;
import com.example.department.DepartmentRepository;
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
        throw new DepartmentNotFoundException("Could not find any Department with ID " + id);
    }

    public void delete(Integer id) throws DepartmentNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new DepartmentNotFoundException("Could not find any Department with ID " + id);
        }
        repo.deleteById(id);
    }
}

