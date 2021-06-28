package com.cashonline.backend.apirest.models.services;

import org.springframework.data.domain.Pageable;

public interface IPaginationService {
    Pageable findBySizeAndPage(Integer size, Integer page);
}