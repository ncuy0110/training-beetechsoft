package com.beetech.mvcspringboot.controller.admin.category.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class CreateCategoryDto {
    @NonNull
    private String name;
}
