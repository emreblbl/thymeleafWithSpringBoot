package com.emreblbl.springbootthymeleaf.demo.service;

import com.emreblbl.springbootthymeleaf.demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int theId);
    public void save(User user);
    public void delete(int theId);
    public List<User> searchForSurname(String surname);
    public List<User> searchWhereAny(String keyword);

}
