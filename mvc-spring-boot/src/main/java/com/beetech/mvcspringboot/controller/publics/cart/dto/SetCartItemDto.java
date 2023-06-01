package com.beetech.mvcspringboot.controller.publics.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.NonNull;


public class SetCartItemDto extends AddingCartItemDto {
    public SetCartItemDto(@NonNull @Min(1) Long productId, @NonNull @Min(1) Long quantity) {
        super(productId, quantity);
    }
}
