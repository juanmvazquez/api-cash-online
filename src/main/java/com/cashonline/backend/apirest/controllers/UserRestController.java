package com.cashonline.backend.apirest.controllers;

import com.cashonline.backend.apirest.models.entity.User;
import com.cashonline.backend.apirest.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<User> index(){
        return userService.findAll();

    }
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User show(@PathVariable int id){
        return userService.findById(id);

    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){ //body Un simple getter usado para exponer un contenido del cuerpo

        return userService.save(user);
    }

    @PutMapping("users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user, @PathVariable int id) {
        User userActual = userService.findById(id);

        userActual.setFirsName(user.getFirstName());
        userActual.setLastName(user.getLastName());
        userActual.setEmail(user.getEmail());
        userActual.setLoans(user.getLoans());

        return userService.save(userActual);
    }

    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

}
