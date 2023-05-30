package com.beetech.mvcspringboot.controller.admin.product.dto;

import lombok.*;

/**
 * The type Product csv dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@ToString
public class ProductCsvDto {
    /**
     * name string
     */
    @NonNull
    private String name;

    /**
     * price Double
     */
    @NonNull
    private Double price;

    /**
     * quantity Long
     */
    @NonNull
    private Long quantity;

    /**
     * description string
     */
    @NonNull
    private String description;

    /**
     * categoryId Long
     */
    @NonNull
    private Long categoryId;

}
