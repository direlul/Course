package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllPerformanceSeats {

    private Long theaterId;
    private Integer blockCode;
    private Integer rowNumber;
    private Integer seatNumber;
    private Integer seatStatusCode;
    private Long bookingId;
    private Long performanceId;
}
