package com.cashonline.backend.apirest.models.dao;

import com.cashonline.backend.apirest.models.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ILoanDao extends PagingAndSortingRepository<Loan, Integer> {
    Page<Loan> findLoanByUserId(Integer id, Pageable pageable);
}
