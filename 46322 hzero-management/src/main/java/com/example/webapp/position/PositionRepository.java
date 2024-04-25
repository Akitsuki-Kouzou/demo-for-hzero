package com.example.webapp.position;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PositionRepository extends CrudRepository<Position, Integer> {
    public long countByPositionId(Integer positionId);
    List<Position> findByPositionId(Integer positionId);
}
