package com.erkebaev.shop.services;

import java.util.List;
import com.erkebaev.shop.model.Option;
import com.erkebaev.shop.model.Value;
import com.erkebaev.shop.repository.OptionsRepository;
import com.erkebaev.shop.repository.ValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionsRepository optionsRepository;
    private final ValueRepository valueRepository;

    public List<Option> listOption() {
        return optionsRepository.findAll();
    }

    public List<Value> listValue() {
        return valueRepository.findAll();
    }
}
