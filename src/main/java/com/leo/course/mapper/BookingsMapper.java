package com.leo.course.mapper;

import com.leo.course.model.Bookings;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingsMapper implements RowMapper<Bookings> {
    @Override
    public Bookings mapRow(ResultSet rs, int i) throws SQLException {
        return new Bookings(rs.getLong("booking_id"), rs.getLong("customer_id"),
                rs.getDate("booking_made_date"), rs.getLong("peformance_id"));
    }
}
