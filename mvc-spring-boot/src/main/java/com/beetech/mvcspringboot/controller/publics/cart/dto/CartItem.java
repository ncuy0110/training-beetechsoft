package com.beetech.mvcspringboot.controller.publics.cart.dto;

import com.beetech.mvcspringboot.model.Product;
import jakarta.validation.constraints.Min;
import lombok.*;


/**
 * The type Cart item.
 */
@Data
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CartItem {
    /**
     * product Product
     */
    @NonNull
    private Product product;

    /**
     * quantity Long
     */
    @NonNull
    @Min(1)
    private Long quantity;

    /**
     * Gets total.
     *
     * @return the total
     */
    public Double getTotal() {
        return product.getPrice() * quantity;
    }
}
