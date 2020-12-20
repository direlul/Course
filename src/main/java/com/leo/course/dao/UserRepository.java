package com.leo.course.dao;

import com.leo.course.model.User;

import java.util.List;

public interface UserRepository {

    public List<User> getUsers();
    public int create(String username, String password, String authority);
    public int delete(String username);

}
