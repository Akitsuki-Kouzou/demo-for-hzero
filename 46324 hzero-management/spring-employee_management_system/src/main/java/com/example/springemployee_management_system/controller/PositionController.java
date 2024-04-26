package com.example.springemployee_management_system.controller;

import com.example.springemployee_management_system.model.Position;
import com.example.springemployee_management_system.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping
    public Iterable<Position> getAllPositions() {
        return positionService.getAllPosition();
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<Position> getPositionById(@PathVariable Integer positionId) {
        return positionService.getPositionById(positionId)
                .map(position -> ResponseEntity.ok().body(position))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        Position savedPosition = positionService.savePosition(position);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPosition);
    }

    @PutMapping("/{positionId}")
    public ResponseEntity<Position> updatePosition(@PathVariable Integer positionId, @RequestBody Position updatedPosition) {
        return positionService.getPositionById(positionId)
                .map(existingPosition -> {
                    existingPosition.setPositionName(updatedPosition.getPositionName());
                    existingPosition.setPositionCode(updatedPosition.getPositionCode());
                    existingPosition.setPositionDepartmentCode(updatedPosition.getPositionDepartmentCode());
                    Position savedPosition = positionService.savePosition(existingPosition);
                    return ResponseEntity.ok().body(savedPosition);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<Void> deletePosition(@PathVariable Integer positionId) {
        positionService.deletePosition(positionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Iterable<Position> searchPositions(@RequestParam String keyword) {
        return positionService.searchPositions(keyword);
    }

    @GetMapping("/searchByDepartment")
    public Iterable<Position> searchPositionsByDepartment(@RequestParam String department) {
        return positionService.searchPositionsByDepartment(department);
    }

    @GetMapping("/searchByPosition")
    public Iterable<Position> searchPositionsByPosition(@RequestParam String position) {
        return positionService.searchPositionsByPosition(position);
    }
}
