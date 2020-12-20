package com.leo.course.mapper;

import com.leo.course.model.Customers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersMapper implements RowMapper<Customers> {


    @Override
    public Customers mapRow(ResultSet rs, int i) throws SQLException {
        return new Customers(rs.getLong("customer_id"), rs.getString("fio"),
                rs.getString("mobile_phone"));
    }
}
