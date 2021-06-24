package com.cashonline.backend.apirest.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class LoanDto implements Serializable {


    private BigDecimal total;
    private int user_id;


    public LoanDto(){
    }

    public LoanDto (BigDecimal total, int user_id) {
        this.total = total;
        this.user_id = user_id;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private static final long serialVersionUID = 1L;

}

