package com.beetech.mvcspringboot.controller.publics.cart.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Min;

@Data
@ToString
public class AddingCartItemDto {
    @NonNull
    @Min(1)
    private Long productId;

    @NonNull
    @Min(1)
    private Long quantity;
}
