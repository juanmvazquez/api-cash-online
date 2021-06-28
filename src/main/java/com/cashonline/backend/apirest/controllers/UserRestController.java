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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
            throw new NotFoundUserException("ERROR El id no se encuentra en la DB");
        }

        List<Loan> loans = user.getLoans();
        List<ItemDto> listItem = new ArrayList<>();

        loans.forEach(loan -> {
            ItemDto itemDto = new ItemDto(loan.getId(), loan.getTotal().intValue(), loan.getUser().getId());
            listItem.add(itemDto);

        });

        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), listItem);

    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

        User userNew;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(e -> "El campo '" + e.getField() + "' " +e.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            userNew = userService.save(user);
        } catch(DataAccessException e){
            response.put("ERROR", "El email ya se encuentra en uso.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("CREADO", "El cliente ha sido creado con exito");
        response.put("user", userNew);
        return new ResponseEntity<>(response, HttpStatus.OK);

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
