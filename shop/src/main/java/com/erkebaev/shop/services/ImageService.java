package com.erkebaev.shop.services;

import java.util.List;
import com.erkebaev.shop.model.Image;
import com.erkebaev.shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> listImages() {
         return  imageRepository.findAll();
    }
}
