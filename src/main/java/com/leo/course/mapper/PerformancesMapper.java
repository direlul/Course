package com.leo.course.mapper;

import com.leo.course.model.Performances;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformancesMapper implements RowMapper<Performances> {

    @Override
    public Performances mapRow(ResultSet rs, int i) throws SQLException {
        return new Performances(rs.getLong("performance_id"), rs.getLong("production_id"),
                rs.getInt("performance_number"), rs.getDate("show_date"));
    }
}
