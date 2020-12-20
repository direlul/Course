package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingForm {

    private String fio;
    private String numberPhone;
    private Long performanceId;
    private Long seatBlock;
    private Long rowSeats;
    private Long seat;
}
