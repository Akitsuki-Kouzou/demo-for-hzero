package com.example.springemployee_management_system.repository;

import com.example.springemployee_management_system.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Iterable<Position> findByPositionNameContainingIgnoreCase(String keyword);
    Iterable<Position> findByPositionDepartmentCodeIgnoreCase(String department);
    Iterable<Position> findByPositionNameIgnoreCase(String position);
}
