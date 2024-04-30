package com.example.demo.position;

import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Integer> {
    public Long countByPositionId(Integer departmentId);
}
