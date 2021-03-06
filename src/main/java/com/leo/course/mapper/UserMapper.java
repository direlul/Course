package com.leo.course.mapper;

import com.leo.course.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        return new User(rs.getString("username"),
                rs.getString("password"),
                rs.getBoolean("enabled"),
                rs.getString("authority"));
    }
}
