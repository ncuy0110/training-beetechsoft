package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Product;
import com.beetech.mvcspringboot.model.ProductImage;
import com.beetech.mvcspringboot.repository.ProductImageRepository;
import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import com.beetech.mvcspringboot.service.interfaces.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final FilesStorageService filesStorageService;
    private final ProductImageRepository productImageRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(List<MultipartFile> images, Product product) {
        try {
            for (MultipartFile image : images) {
                String filename = filesStorageService.save(image);
                ProductImage productImage = new ProductImage(filename, image.getOriginalFilename(), image.getContentType(), product);
                productImageRepository.save(productImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
