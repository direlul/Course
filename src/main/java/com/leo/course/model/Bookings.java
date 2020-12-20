package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Bookings {
    Long bookingId;
    Long customerId;
    Date bookingMadeDate;
    Long performanceId;
}
