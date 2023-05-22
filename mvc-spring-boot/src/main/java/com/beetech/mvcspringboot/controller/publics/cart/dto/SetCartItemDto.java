package com.beetech.mvcspringboot.controller.publics.cart.dto;

import lombok.NonNull;

import javax.validation.constraints.Min;

public class SetCartItemDto extends AddingCartItemDto {
    public SetCartItemDto(@NonNull @Min(1) Long productId, @NonNull @Min(1) Long quantity) {
        super(productId, quantity);
    }
}
