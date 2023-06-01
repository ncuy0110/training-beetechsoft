package com.beetech.mvcspringboot.controller.publics.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.NonNull;


/**
 * The type Set cart item dto.
 */
public class SetCartItemDto extends AddingCartItemDto {
    /**
     * Instantiates a new Set cart item dto.
     *
     * @param productId the product id
     * @param quantity  the quantity
     */
    public SetCartItemDto(@NonNull @Min(1) Long productId, @NonNull @Min(1) Long quantity) {
        super(productId, quantity);
    }
}
