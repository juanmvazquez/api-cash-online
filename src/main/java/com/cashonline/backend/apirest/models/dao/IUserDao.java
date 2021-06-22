package com.cashonline.backend.apirest.models.dao;

import com.cashonline.backend.apirest.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {

}
