package com.cashonline.backend.apirest.models.dao;

import com.cashonline.backend.apirest.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


// es una interfaz para generar operaciones CRUD de un repositorio de acceso de datos
public interface IUserDao extends JpaRepository<User, Integer> {

}
