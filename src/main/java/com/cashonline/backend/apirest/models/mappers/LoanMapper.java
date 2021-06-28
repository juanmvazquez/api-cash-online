package com.cashonline.backend.apirest.models.mappers;

import com.cashonline.backend.apirest.controllers.dto.ItemDto;
import com.cashonline.backend.apirest.controllers.dto.LoanResponseDto;
import com.cashonline.backend.apirest.controllers.dto.PagingDto;
import com.cashonline.backend.apirest.models.entity.Loan;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class LoanMapper {

    public LoanResponseDto mapToDto(List<Loan> loans, Pageable page){
        PagingDto pagingDto = new PagingDto(page.getPageNumber() + 1, page.getPageSize(), loans.size());
        List<ItemDto> listItem = new ArrayList<>();
        loans.forEach(loan -> {
            ItemDto itemDto = new ItemDto(loan.getId(), loan.getTotal().intValue(), loan.getUser().getId());
            listItem.add(itemDto);
        });

        return new LoanResponseDto(listItem, pagingDto);
    }

}
