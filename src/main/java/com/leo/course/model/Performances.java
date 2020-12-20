package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Performances {
    Long performanceId;
    Long productionId;
    Integer performanceNumber;
    Date showDate;
}
