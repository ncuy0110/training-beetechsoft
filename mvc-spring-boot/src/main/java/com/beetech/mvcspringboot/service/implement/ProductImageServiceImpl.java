package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Product;
import com.beetech.mvcspringboot.model.ProductImage;
import com.beetech.mvcspringboot.repository.ProductImageRepository;
import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import com.beetech.mvcspringboot.service.interfaces.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageServiceImpl.class);
    private final FilesStorageService filesStorageService;
    private final ProductImageRepository productImageRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(List<MultipartFile> images, Product product) {
        try {
            List<ProductImage> productImages = images.stream()
                    .map(image -> {
                        String filename = filesStorageService.save(image);
                        return new ProductImage(filename, image.getOriginalFilename(), image.getContentType(), product);
                    })
                    .toList();

            productImageRepository.saveAll(productImages);
        } catch (
                Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Create product image error: {}", e.getMessage());
            }
            throw new ServerErrorException("Server error: ", e);
        }
    }
}
