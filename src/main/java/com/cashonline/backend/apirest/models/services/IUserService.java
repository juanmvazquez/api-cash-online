package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.models.entity.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(int id);

    User save (User user);

    void delete(int id);

}
