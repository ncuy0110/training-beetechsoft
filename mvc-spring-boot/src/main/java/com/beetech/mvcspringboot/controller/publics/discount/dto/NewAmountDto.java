package com.beetech.mvcspringboot.controller.publics.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * The type New amount dto.
 */
@Data
@AllArgsConstructor
public class NewAmountDto {
    /**
     * newAmount Double
     */
    @NonNull
    private Double newAmount;
}
