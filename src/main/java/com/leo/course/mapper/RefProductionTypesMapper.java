package com.leo.course.mapper;

import com.leo.course.model.RefProductionTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefProductionTypesMapper implements RowMapper<RefProductionTypes> {
    @Override
    public RefProductionTypes mapRow(ResultSet rs, int i) throws SQLException {
        return new RefProductionTypes(rs.getLong("production_type_id"),
                rs.getString("production_type_description"));
    }
}
