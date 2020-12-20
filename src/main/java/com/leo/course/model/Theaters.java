package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Theaters {
    Long theaterId;
    String theaterName;
    String theaterManager;
    String theaterPhone;
    String theaterAddress;
    String otherDetails;
}
