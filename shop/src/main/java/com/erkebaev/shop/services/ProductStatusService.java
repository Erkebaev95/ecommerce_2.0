package com.erkebaev.shop.services;

import java.util.List;
import com.erkebaev.shop.model.ProductStatus;
import com.erkebaev.shop.repository.ProductStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductStatusService {
    private final ProductStatusRepository statusRepository;

    public List<ProductStatus> statusList() {
        return statusRepository.findAll();
    }
}
