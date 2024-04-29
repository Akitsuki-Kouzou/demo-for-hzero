package com.example.springdemo.Repos;

import com.example.springdemo.Entities.DepartmentEntity;
import com.example.springdemo.Entities.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.Position;
import java.util.List;

public interface PositionRepo  extends JpaRepository<PositionEntity, String>, JpaSpecificationExecutor<PositionEntity> {

    @Query(value =
            "SELECT COUNT(*) FROM ms_position WHERE LOWER(position_code) = LOWER(:positionCode)",
            nativeQuery = true)
    long countById(@Param("positionCode") String positionCode);

    @Query(value = "SELECT * FROM ms_positon "
            + " WHERE LOWER(position_code) LIKE %:positionCode% "
            + " AND LOWER(position_name) LIKE %:positionName% ", nativeQuery = true)
    List<PositionEntity> searchPosition(@Param("positionCode") String positionCode,
                                         @Param("positionName") String positionName);

}
