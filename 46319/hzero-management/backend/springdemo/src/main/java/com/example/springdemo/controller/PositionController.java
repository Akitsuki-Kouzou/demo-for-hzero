package com.example.springdemo.controller;

import com.example.springdemo.model.Position;
import com.example.springdemo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService; // Corrected: Inject the service

    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.getAllPositions(); // Delegate to the service
    }


    @GetMapping("/search")
    public List<Position> searchByName(@RequestParam String name) {
        return positionService.searchByName(name);
    }
//    @GetMapping("/{positionCode}")
//    public Position getPositionById(@PathVariable String positionCode) {
//        return positionService.findPositionById(positionCode);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Position createPosition(@RequestBody Position position) {
        return positionService.savePosition(position);
    }

    @PutMapping("/{positionCode}")
    public Position updatePosition(@PathVariable String positionCode, @RequestBody Position newPosition) {
        // ... Add logic to update
        return positionService.updatePosition(newPosition);
    }

    @DeleteMapping("/{positionCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosition(@PathVariable String positionCode) {
        positionService.deletePosition(positionCode);
    }
}
