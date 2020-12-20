package com.leo.course.mapper;

import com.leo.course.model.PerformanceView;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceViewMapper implements RowMapper<PerformanceView> {
    @Override
    public PerformanceView mapRow(ResultSet rs, int i) throws SQLException {
        return new PerformanceView(rs.getLong("performance_id"), rs.getDate("show_date"),
                rs.getLong("performance_number"), rs.getString("production_name"),
                rs.getString("production_description"));
    }
}
