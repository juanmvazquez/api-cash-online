package com.cashonline.backend.apirest.models.services;

import com.cashonline.backend.apirest.models.dao.ILoanDao;
import com.cashonline.backend.apirest.models.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService implements ILoanService{

    @Autowired
    private ILoanDao loanDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Loan> findAll(Pageable pageable) {
        return loanDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Loan findById(int id) {
        return loanDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Loan> findLoanByUserId(Integer id, Pageable pageable)  {
        return loanDao.findLoanByUserId(id,pageable);
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
