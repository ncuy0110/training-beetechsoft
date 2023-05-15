package com.beetech.mvcspringboot.controller.publics.cart.dto;

import com.beetech.mvcspringboot.model.Product;
import lombok.*;

import javax.validation.constraints.Min;

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
