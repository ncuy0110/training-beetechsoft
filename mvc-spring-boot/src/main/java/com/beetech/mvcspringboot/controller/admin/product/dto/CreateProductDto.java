package com.beetech.mvcspringboot.controller.admin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    private String name;
    private String description;
    private Double price;
    private Long quantity;
    private Long categoryId;
    private List<MultipartFile> images;
}
