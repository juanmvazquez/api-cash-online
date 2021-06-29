package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.controllers.dto.LoanResponseDto;
import com.cashonline.backend.apirest.models.entity.Loan;

public interface ILoanService {

    LoanResponseDto findAll(Integer size, Integer page);
    LoanResponseDto findLoanByUserId(Integer id, Integer size, Integer page);

    Loan findById(int id);

    Loan save (Loan loan);

    void delete(int id);

}
