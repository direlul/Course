package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@Data
@AllArgsConstructor
public class PerformanceNumbers {
    Long performanceNumber;
    Time performanceStartTime;
    Time performanceEndTime;
}
