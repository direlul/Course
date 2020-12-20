package com.leo.course.mapper;

import com.leo.course.model.AllPerformanceSeats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllPerformanceSeatsMapper implements RowMapper<AllPerformanceSeats> {

    @Override
    public AllPerformanceSeats mapRow(ResultSet rs, int i) throws SQLException {
        Long theaterId = rs.getLong("theater_id");
        Integer blockCode = rs.getInt("block_code");
        Integer rowNumber = rs.getInt("row_number");
        Integer seatNumber = rs.getInt("seat_number");
        Integer seatStatusCode = rs.getInt("seat_status_code");
        Long bookingId = rs.getLong("booking_id");
        Long performanceId = rs.getLong("performance_id");

        return new AllPerformanceSeats(theaterId, blockCode, rowNumber, seatNumber, seatStatusCode,
                bookingId, performanceId);
    }
}
