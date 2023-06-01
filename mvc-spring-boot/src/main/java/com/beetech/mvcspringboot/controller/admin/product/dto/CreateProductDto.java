package com.beetech.mvcspringboot.controller.admin.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Create product dto.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class CreateProductDto {
    /**
     * name string
     */
    @NotBlank
    private String name;

    /**
     * description string
     */
    @NotBlank
    private String description;

    /**
     * price Double
     */
    private Double price;

    /**
     * quantity Long
     */
    private Long quantity;

    /**
     * categoryId long
     */
    private Long categoryId;

    /**
     * images List<MultipartFile>
     */
    private List<MultipartFile> images;

    /**
     * Instantiates a new Creation product dto.
     *
     * @param productCsvDto the product csv dto
     */
    public CreateProductDto(ProductCsvDto productCsvDto) {
        setName(productCsvDto.getName());
        setDescription(productCsvDto.getDescription());
        setPrice(productCsvDto.getPrice());
        setQuantity(productCsvDto.getQuantity());
        setCategoryId(productCsvDto.getCategoryId());
        setImages(new ArrayList<>());
    }
}
