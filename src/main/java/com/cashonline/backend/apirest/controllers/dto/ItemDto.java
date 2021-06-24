package com.cashonline.backend.apirest.controllers.dto;

public class ItemDto {

    private Integer id;
    private Integer total;
    private Integer userId;

    public ItemDto() {
    }

    public ItemDto(Integer id, Integer total, Integer userId) {
        this.id = id;
        this.total = total;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
