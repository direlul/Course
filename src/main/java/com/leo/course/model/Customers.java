package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customers {
    Long customerId;
    String fio;
    String mobilePhone;
}
