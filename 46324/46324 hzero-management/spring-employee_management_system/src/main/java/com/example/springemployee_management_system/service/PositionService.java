package com.example.springemployee_management_system.service;

import com.example.springemployee_management_system.model.Position;
import com.example.springemployee_management_system.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {
    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Iterable<Position> getAllPosition() {
        return positionRepository.findAll();
    }

    public Optional<Position> getPositionById(Integer positionId) {
        return positionRepository.findById(positionId);
    }

    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    public void deletePosition(Integer positionId) {
        positionRepository.deleteById(positionId);
    }

    public Iterable<Position> searchPositions(String keyword) {
        return positionRepository.findByPositionNameContainingIgnoreCase(keyword);
    }

    public Iterable<Position> searchPositionsByDepartment(String department) {
        return positionRepository.findByPositionDepartmentCodeIgnoreCase(department);
    }

    public Iterable<Position> searchPositionsByPosition(String position) {
        return positionRepository.findByPositionNameIgnoreCase(position);
    }
}
