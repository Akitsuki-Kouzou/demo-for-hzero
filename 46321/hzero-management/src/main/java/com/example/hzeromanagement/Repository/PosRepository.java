package com.example.hzeromanagement.Repository;

import com.example.hzeromanagement.Model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PosRepository extends JpaRepository<EmployeePosition, Long>{
    List<EmployeePosition> findByPosNameLike(String posName);

}
