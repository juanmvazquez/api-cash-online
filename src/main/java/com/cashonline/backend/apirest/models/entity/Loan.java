package com.cashonline.backend.apirest.models.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "loan")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnoreProperties(value = "loans")
    @JoinColumn(name="user_id",nullable = false)
    private User user ;

    public Loan(User user, BigDecimal total) {
        this.user = user;
        this.total = total;
    }

    @Column
    private BigDecimal total;

    public Loan() {

    }

    public BigDecimal getTotal() {

        return total;
    }

    public void setTotal(BigDecimal total) {

        this.total = total;
    }

    public void setId(int id) {

        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getId() {
        return id;
    }

}
