package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TheatricalProductions {
    Long productionId;
    Long productionTypeId;
    Long theaterId;
    String productionName;
    String productionDescription;
    String otherDetails;
}
