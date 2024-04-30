package com.example.position;

import com.example.position.Position;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Integer>, JpaSpecificationExecutor<Position> {
    public Long countById(Integer id);
}