package com.cashonline.backend.apirest.models.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationService implements IPaginationService {

    public Pageable findBySizeAndPage(Integer size, Integer page){
        page = page -1;
        if (page < 0) {
            page = 0;
        }

        return PageRequest.of(page,size);
    }
}
