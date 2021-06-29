package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.controllers.dto.LoanResponseDto;
import com.cashonline.backend.apirest.models.dao.ILoanDao;
import com.cashonline.backend.apirest.models.entity.Loan;
import com.cashonline.backend.apirest.models.mappers.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService implements ILoanService{

    @Autowired
    private ILoanDao loanDao;

    @Autowired
    private IPaginationService paginationService;

    @Autowired
    private LoanMapper loanMapper;


    @Override
    @Transactional(readOnly = true)
    public LoanResponseDto findAll(Integer size, Integer page) {
        Pageable pageRequest = this.paginationService.findBySizeAndPage(size, page);
        Page<Loan> loans = loanDao.findAll(pageRequest);
        return this.loanMapper.mapToDto(loans.getContent(), pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Loan findById(int id) {
        return loanDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanResponseDto findLoanByUserId(Integer id, Integer size, Integer page) {
        Pageable pageRequest = this.paginationService.findBySizeAndPage(size, page);
        Page<Loan> loans = loanDao.findLoanByUserId(id, pageRequest);
        return this.loanMapper.mapToDto(loans.getContent(), pageRequest);
    }

    @Override
    @Transactional
    public Loan save(Loan loan) {
         return loanDao.save(loan);
    }

    @Override
    @Transactional
    public void delete(int id) {
        loanDao.deleteById(id);
    }
}
