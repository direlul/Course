package com.leo.course.mapper;

import com.leo.course.model.Theaters;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheatersMapper implements RowMapper<Theaters> {
    @Override
    public Theaters mapRow(ResultSet rs, int i) throws SQLException {
        return new Theaters(rs.getLong("theater_id"), rs.getString("theater_name"),
                rs.getString("theater_manager"), rs.getString("theater_phone"),
                rs.getString("theater_address"), rs.getString("other_details"));
    }
}
