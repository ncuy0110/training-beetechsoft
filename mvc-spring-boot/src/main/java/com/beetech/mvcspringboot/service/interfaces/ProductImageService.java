package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductImageService {
    void create(List<MultipartFile> images, Product product);
}
