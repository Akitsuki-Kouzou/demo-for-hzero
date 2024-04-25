package com.example.webapp.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired private PositionRepository repo;

    public List<Position> listAll(){
        return (List<Position>) repo.findAll();
    }

    public void save(Position position) {
        repo.save(position);
    }

    public Position get(Integer positionId) throws PositionNotFoundException {
        Optional<Position> result = repo.findById(positionId);
        if(result.isPresent()){
            return result.get();
        }
        throw new PositionNotFoundException("Could not find any position with ID " + positionId);
    }

    public void delete(Integer positionId) throws PositionNotFoundException {
        Long count = repo.countByPositionId(positionId);
        if(count == null || count == 0){
            throw new PositionNotFoundException("Could not find any position with ID " + positionId);
        }
        repo.deleteById(positionId);
    }
}
