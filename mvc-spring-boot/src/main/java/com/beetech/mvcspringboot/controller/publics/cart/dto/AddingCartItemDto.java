package com.beetech.mvcspringboot.controller.publics.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;


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
