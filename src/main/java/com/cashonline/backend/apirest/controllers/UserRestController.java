package com.cashonline.backend.apirest.controllers;

import com.cashonline.backend.apirest.controllers.dto.ItemDto;
import com.cashonline.backend.apirest.controllers.dto.UserDto;
import com.cashonline.backend.apirest.controllers.exceptions.NotFoundUserException;
import com.cashonline.backend.apirest.models.entity.Loan;
import com.cashonline.backend.apirest.models.entity.User;
import com.cashonline.backend.apirest.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public UserDto show(@PathVariable int id) {
        User user = userService.findById(id);

        if(user == null){
            throw new NotFoundUserException("ERROR El id no existe en la DB.");
        }

        List<Loan> loans = user.getLoans();
        List<ItemDto> listItem = new ArrayList<>();

        for (Loan loan: loans) {
            ItemDto itemDto = new ItemDto(loan.getId(), loan.getTotal().intValue(), loan.getUser().getId());
            listItem.add(itemDto);
        }
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), listItem);

        return userDto;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) {

        User userNew = null;
        Map<String, Object> response = new HashMap<>();

        try{
            userNew = userService.save(user);
        } catch(DataAccessException e){
            response.put("ERROR", "No se pudo crear un nuevo usuario.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("CREADO", "¡El cliente ha sido cargado correctamente!");
        response.put("user", userNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete (@PathVariable int id) {

        Map<String, Object> response = new HashMap<>();

        try{
            userService.delete(id);
        } catch(DataAccessException e){
            response.put("ERROR", "El id no existe en la DB.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("ELIMINADO", "¡El cliente ha sido borrado correctamente!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
