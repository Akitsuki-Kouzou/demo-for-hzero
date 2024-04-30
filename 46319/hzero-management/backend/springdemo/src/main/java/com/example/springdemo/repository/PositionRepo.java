package com.example.springdemo.repository;

import com.example.springdemo.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Optional;
import java.util.List;


@Repository
public class PositionRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Position> findAllPositions() {
        String sql = "SELECT position_code, position_name, department_code FROM emp_position";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
                new Position(
                        resultSet.getString("position_code"),
                        resultSet.getString("position_name"),
                        resultSet.getString("department_code")
                ));
    }

    public Position savePosition(Position position) {
        String sql = "INSERT INTO emp_position (position_code, position_name, department_code) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                position.getPosition_code(),
                position.getPosition_name(),
                position.getDepartment_code());
        return position;
    }

    public Optional<Position> findById(String positionCode) {
        String sql = "SELECT * FROM emp_position WHERE position_code = ?";
        try {
            Position position = jdbcTemplate.queryForObject(sql, new Object[]{positionCode},
                    (rs, rowNum) -> new Position(
                            rs.getString("position_code"),
                            rs.getString("position_name"),
                            rs.getString("department_code")
                    ));
            return Optional.of(position);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void updatePosition(Position position) {
        String sql = "UPDATE emp_position SET position_name = ?, department_code = ? WHERE position_code = ?";
        jdbcTemplate.update(sql,
                position.getPosition_name(),
                position.getDepartment_code(),
                position.getPosition_code());
    }

    public void deletePosition(String positionCode) {
        String sql = "DELETE FROM emp_position WHERE position_code = ?";
        jdbcTemplate.update(sql, positionCode);
    }
}
