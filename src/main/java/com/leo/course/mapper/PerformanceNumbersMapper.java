package com.leo.course.mapper;

import com.leo.course.model.PerformanceNumbers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceNumbersMapper implements RowMapper<PerformanceNumbers> {
    @Override
    public PerformanceNumbers mapRow(ResultSet rs, int i) throws SQLException {
        return new PerformanceNumbers(rs.getLong("performance_number"), rs.getTime("performance_start_time"),
                rs.getTime("performance_end_time"));
    }
}
