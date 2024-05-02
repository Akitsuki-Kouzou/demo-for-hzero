package com.example.springdemo.Services;


import com.alibaba.fastjson.JSON;
import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Exceptions.ClientException;
import com.example.springdemo.Exceptions.NotFoundException;
import com.example.springdemo.Mappers.DepartmentMapper;
import com.example.springdemo.Models.DepartmentModel;
import com.example.springdemo.Validations.Validator;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    Validator validator = new Validator();


    public List<DepartmentEntity> searchDepartment(DepartmentModel departmentModel){

        List<DepartmentEntity> departments = new ArrayList<>();

        String jsonString = redisTemplate.opsForValue().get("departmentEntity:"
                +departmentModel.getDepartmentCode()+":"
                +departmentModel.getDepartmentName());

        if (!StringUtils.isEmpty(jsonString)) {
            departments = JSON.parseObject(jsonString, ArrayList.class);
        }
        else {
            departments = departmentMapper.searchDepartment("%"+departmentModel.getDepartmentCode().toLowerCase()+"%",
                    "%"+departmentModel.getDepartmentName().toLowerCase()+"%");
            //System.out.println(departments.get(0).getDepartmentCode()+" "+departments.get(0).getDepartmentName());

            if(null != departments){
                redisTemplate.opsForValue().set("departmentEntity:"
                        +departmentModel.getDepartmentCode()+":"
                        +departmentModel.getDepartmentName(),
                        JSON.toJSONString(departments),30, TimeUnit.SECONDS );
            }
        }

        return departments;
    }

    public List<DepartmentEntity> getAllDepartment() {

        List<DepartmentEntity> departments = new ArrayList<>();

        String jsonString = redisTemplate.opsForValue().get("departmentEntity:all");

        if (!StringUtils.isEmpty(jsonString)) {
            departments = JSON.parseObject(jsonString, ArrayList.class);
        } else {
            departments = departmentMapper.getAllDepartment();

            if (null != departments) {
                redisTemplate.opsForValue().set("departmentEntity:all",
                        JSON.toJSONString(departments), 30, TimeUnit.SECONDS);
            }
        }

        return departments;
    }

    public DepartmentEntity getDepartmentByCode(String code) throws NotFoundException {
        DepartmentEntity department = new DepartmentEntity();

        String jsonString = redisTemplate.opsForValue().get("departmentEntity:"+code);

        if (!StringUtils.isEmpty(jsonString)) {
            department = JSON.parseObject(jsonString, DepartmentEntity.class);
        }
        else {
            department = departmentMapper.getDepartmentByCode(code);
            //System.out.println(department.getDepartmentCode()+department.getDepartmentName());
            validator.checkNullObject(department);

            if(null != department){
                redisTemplate.opsForValue().set("departmentEntity:"+code,
                        JSON.toJSONString(department),30, TimeUnit.SECONDS );
            }
        }

        return department;
    }

    public void createDepartment(DepartmentModel departmentModel) throws ClientException {
        DepartmentEntity department = new DepartmentEntity();

        Long count = departmentMapper.countById(departmentModel.getDepartmentCode().toLowerCase());
        if(count > 0) throw new ClientException("Department Code already exist!");

        department.setDepartmentCode(departmentModel.getDepartmentCode());
        department.setDepartmentName(departmentModel.getDepartmentName());

        departmentMapper.createDepartment(department);
    }

    public void updateDepartment(DepartmentModel departmentModel) throws NotFoundException {
        DepartmentEntity department = getDepartmentByCode(departmentModel.getDepartmentCode());
        validator.checkNullObject(department);

        department.setDepartmentName(departmentModel.getDepartmentName());

        departmentMapper.updateDepartment(department);

    }

    public void deleteDepartment(String departmentCode) throws NotFoundException {
        DepartmentEntity department = getDepartmentByCode(departmentCode);
        validator.checkNullObject(department);

        departmentMapper.deleteDepartment(departmentCode);
    }


}
