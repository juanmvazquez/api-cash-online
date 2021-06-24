package com.cashonline.backend.apirest.controllers.dto;

import java.util.List;

public class UserDto {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private List<ItemDto> loans;

    public UserDto() {
    }

    public UserDto(Integer id, String email, String firstName, String lastName, List<ItemDto> loans) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loans = loans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ItemDto> getLoans() {
        return loans;
    }

    public void setLoans(List<ItemDto> loans) {
        this.loans = loans;
    }
}
