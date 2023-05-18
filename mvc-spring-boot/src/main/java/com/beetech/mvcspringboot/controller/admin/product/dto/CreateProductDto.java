package com.beetech.mvcspringboot.controller.admin.product.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class CreateProductDto {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private Long quantity;
    @NonNull
    private Long categoryId;
    private List<MultipartFile> images;

    public CreateProductDto(ProductCsvDto productCsvDto) {
        setName(productCsvDto.getName());
        setDescription(productCsvDto.getDescription());
        setPrice(productCsvDto.getPrice());
        setQuantity(productCsvDto.getQuantity());
        setCategoryId(productCsvDto.getCategoryId());
        setImages(new ArrayList<>());
    }
}
