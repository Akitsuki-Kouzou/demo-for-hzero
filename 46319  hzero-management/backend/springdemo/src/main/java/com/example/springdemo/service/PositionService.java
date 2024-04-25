package com.example.springdemo.service;

import com.example.springdemo.model.Position;
import com.example.springdemo.repository.PositionRepo;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepo positionRepo;

    public List<Position> getAllPositions() {
        return positionRepo.findAllPositions();
    }

    public List<Position> searchByName(String name) {
        List<Position> allPositions = positionRepo.findAllPositions(); // Replace with your fetch method
        List<Position> fuzzySearchResult = new ArrayList<>();

        JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();

        for (Position position : allPositions) {
            double similarityScore = jaroWinkler.apply(position.getPosition_name(), name); // Assuming you search on position name
            if (similarityScore >= 0.75) { // You can adjust this threshold
                fuzzySearchResult.add(position);
            }
        }

        return fuzzySearchResult;
    }

    public Position savePosition(Position position) {
        return positionRepo.savePosition(position);
    }

    public Position findPositionById(String positionCode) {
        Optional<Position> optionalPosition = positionRepo.findById(positionCode);
        return optionalPosition.orElse(null); // Or any other handling if not found
    }

    public Position updatePosition(Position position) {
        positionRepo.updatePosition(position);
        return position;
    }

    public void deletePosition(String positionCode) {
        positionRepo.deletePosition(positionCode);
    }
}
