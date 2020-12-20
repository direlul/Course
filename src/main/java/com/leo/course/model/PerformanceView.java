package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PerformanceView {
    Long id;
    Date showDate;
    Long performanceNumber;
    String name;
    String description;
}
