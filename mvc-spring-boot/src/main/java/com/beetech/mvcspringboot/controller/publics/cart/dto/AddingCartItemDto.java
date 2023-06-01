package com.beetech.mvcspringboot.controller.publics.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;


/**
 * The type Adding cart item dto.
 */
@Data
@ToString
public class AddingCartItemDto {
    /**
     * productId Long
     */
    @NonNull
    @Min(1)
    private Long productId;

    /**
     * quantity Long
     */
    @NonNull
    @Min(1)
    private Long quantity;
}
