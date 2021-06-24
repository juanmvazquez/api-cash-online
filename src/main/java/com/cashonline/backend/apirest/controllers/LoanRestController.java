package com.cashonline.backend.apirest.controllers;

import com.cashonline.backend.apirest.controllers.dto.ItemDto;
import com.cashonline.backend.apirest.controllers.dto.LoanDto;
import com.cashonline.backend.apirest.controllers.dto.LoanResponseDto;
import com.cashonline.backend.apirest.controllers.dto.PagingDto;
import com.cashonline.backend.apirest.models.entity.Loan;
import com.cashonline.backend.apirest.models.entity.User;
import com.cashonline.backend.apirest.models.services.ILoanService;
import com.cashonline.backend.apirest.models.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class LoanRestController {

    @Autowired
    private ILoanService loanService;

    @Autowired
    private IUserService userService;


    @GetMapping("/loans")
    public LoanResponseDto index(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                 @RequestParam(name = "size",defaultValue = "50") Integer size,
                                 @RequestParam(name = "user_id", required = false, defaultValue = "-1") Integer user_id) {

        page = page -1;
        if (page < 0) {
            page = 0;
        }

        Pageable pageRequest = PageRequest.of(page,size);
        Page<Loan> loans = null;

        if (user_id > 0) {
            loans = loanService.findLoanByUserId(user_id,pageRequest);
        }

        else {
            loans = loanService.findAll(pageRequest);
        }

        List<Loan> loansContent = loans.getContent();

        PagingDto pagingDto = new PagingDto(page + 1, size, loansContent.size());

        List<ItemDto> listItem = new ArrayList<>();

        for (Loan loan: loansContent) {
            ItemDto itemDto = new ItemDto(loan.getId(), loan.getTotal().intValue(), loan.getUser().getId());
            listItem.add(itemDto);
        }

        LoanResponseDto loanResponseDto = new LoanResponseDto(listItem, pagingDto);
        return loanResponseDto;
    }

    @GetMapping("/loans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Loan show(@PathVariable int id){
        return loanService.findById(id);

    }

    @PostMapping("/loans")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan create(@RequestBody LoanDto loanDto) {
        User user = userService.findById(loanDto.getUser_id());
        if (user != null) {
            Loan loan = new Loan(user, loanDto.getTotal());
            return loanService.save(loan);
        }
        return null;
    }


    @DeleteMapping("loans/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        loanService.delete(id);
    }

}
