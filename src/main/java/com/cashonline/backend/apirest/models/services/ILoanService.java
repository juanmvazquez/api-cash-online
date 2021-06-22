package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.models.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ILoanService {

    public Page<Loan> findAll(Pageable pageable);

    public Page<Loan> findLoanByUserId(Integer id, Pageable pageable);

    public Loan findById(int id);

    public Loan save (Loan loan);

    public void delete(int id);

}
