package com.leo.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String username;
    String password;
    Boolean enabled;
    String authority;
}
