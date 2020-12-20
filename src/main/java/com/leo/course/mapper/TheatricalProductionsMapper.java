package com.leo.course.mapper;

import com.leo.course.model.TheatricalProductions;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheatricalProductionsMapper implements RowMapper<TheatricalProductions> {
    @Override
    public TheatricalProductions mapRow(ResultSet rs, int i) throws SQLException {
        return new TheatricalProductions(rs.getLong("production_id"),
                rs.getLong("production_type_id"),
                rs.getLong("theater_id"),
                rs.getString("production_name"),
                rs.getString("production_description"),
                rs.getString("other_details"));
    }
}
