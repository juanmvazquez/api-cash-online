package com.cashonline.backend.apirest.controllers.dto;

import java.util.List;

public class LoanResponseDto {

    private List<ItemDto> items;
    private PagingDto paging;


    public LoanResponseDto() {
    }

    public LoanResponseDto(List<ItemDto> items, PagingDto paging) {
        this.items = items;
        this.paging = paging;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public PagingDto getPaging() {
        return paging;
    }

    public void setPaging(PagingDto paging) {
        this.paging = paging;
    }
}