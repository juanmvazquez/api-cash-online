package com.cashonline.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotEmpty(message = "no debe estar vacio.")
    @Size(min=3, max=25, message = "debe tener entre 3 y 15 caracteres.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "no debe estar vacio.")
    @Size(min=3, max=25, message = "debe tener entre 3 y 20 caracteres.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "no debe estar vacio.")
    @Email(message = "es un formato invalido de email.")
    @Column(nullable = false, unique = true)
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user", targetEntity = Loan.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "user")
    private List<Loan> loans = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void setFirsName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
