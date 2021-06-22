package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.models.entity.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User findById(int id);

    public User save (User user);

    public void delete(int id);

}
