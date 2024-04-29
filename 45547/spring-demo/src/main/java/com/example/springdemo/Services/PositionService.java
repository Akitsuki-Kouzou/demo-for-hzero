package com.example.springdemo.Services;

import com.example.springdemo.Entities.PositionEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Models.PositionModel;
import com.example.springdemo.Repos.PositionRepo;
import com.example.springdemo.Validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepo positionRepo;

    Validator validator = new Validator();

    public PositionEntity addPosition(PositionModel positionModel) throws ClientException {
        Long count = positionRepo.countById(positionModel.getPositionCode());
        if(count > 0) throw new ClientException("Position code already exists");

        PositionEntity position = new PositionEntity();
        position.setPositionCode(positionModel.getPositionCode());
        position.setPositionName(positionModel.getPositionName());
        position.setDepartmentCode(positionModel.getDepartmentCode());

        return positionRepo.save(position);
    }

    public List<PositionEntity> getAllPosition(){
        List<PositionEntity> positions = new ArrayList<>();
        positionRepo.findAll().forEach(positions::add);

        return positions;
    }

    public PositionEntity getPositionByCode(String code) throws NotFoundException {
        PositionEntity position = positionRepo.findById(code).orElse(null);
        if(position == null){
            throw new NotFoundException("Department not found!");
        }

        return position;
    }

    public List<PositionEntity> searchPosition(PositionModel positionModel){
        List<PositionEntity> positions = new ArrayList<>();
        positions = positionRepo.searchPosition(positionModel.getPositionCode(), positionModel.getPositionName());

        return positions;
    }

    public PositionEntity updatePosition(PositionModel positionModel) throws NotFoundException {
        PositionEntity position = new PositionEntity();

        if(!positionRepo.existsById(positionModel.getPositionCode())){
            throw new NotFoundException("Cannot found position with code : "+ positionModel.getPositionCode());
        }
        position.setPositionCode(positionModel.getPositionCode());

        if(positionModel.getPositionName() != null){
            position.setPositionName(positionModel.getPositionName());
        }

        if(positionModel.getDepartmentCode() != null){
            position.setDepartmentCode(positionModel.getDepartmentCode());
        }

        return positionRepo.save(position);
    }

    public PositionEntity deletePosition(PositionModel positionModel) throws NotFoundException {
        PositionEntity position = new PositionEntity();

        if(!positionRepo.existsById(positionModel.getPositionCode())){
            throw new NotFoundException("Cannot found position with code : "+ positionModel.getPositionCode());
        }

        position = positionRepo.findById(positionModel.getPositionName()).orElse(null);
        positionRepo.deleteById(positionModel.getPositionName());

        return position;
    }
}
