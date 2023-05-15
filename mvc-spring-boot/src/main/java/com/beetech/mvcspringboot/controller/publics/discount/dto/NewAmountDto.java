package com.beetech.mvcspringboot.controller.publics.discount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class NewAmountDto {
    @NonNull
    private Double newAmount;
}
