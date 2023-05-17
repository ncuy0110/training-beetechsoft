package com.beetech.mvcspringboot.controller.admin.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
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
