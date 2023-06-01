package com.beetech.mvcspringboot.controller.admin.product.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ProductCsvDto {
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private Long quantity;
    @NonNull
    private String description;
    @NonNull
    private Long categoryId;

    public ProductCsvDto() {
    }
}
