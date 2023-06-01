package com.beetech.mvcspringboot.controller.publics.cart.dto;

import com.beetech.mvcspringboot.model.Product;
import jakarta.validation.constraints.Min;
import lombok.*;


@Data
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CartItem {
    @NonNull
    private Product product;
    @NonNull
    @Min(1)
    private Long quantity;

    public Double getTotal() {
        return product.getPrice() * quantity;
    }
}
