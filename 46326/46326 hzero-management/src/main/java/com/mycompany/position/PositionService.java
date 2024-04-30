package com.mycompany.position;

import com.mycompany.department.Department;
import com.mycompany.department.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired private PositionRepository repo;

    public List<Position> listAll() {
        return (List<Position>) repo.findAll();
    }

    public void save(Position position) {
        repo.save(position);
    }

    public Position get(Integer id) throws PositionNotFoundException {
        Optional<Position> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PositionNotFoundException("Could not find any position with ID " + id);
    }

    public void delete(Integer id) throws PositionNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new PositionNotFoundException("Could not find any position with ID " + id);
        }
        repo.deleteById(id);
    }
}