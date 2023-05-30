package com.beetech.mvcspringboot.controller.admin.category.dto;

import lombok.*;

/**
 * The type Create category dto.
 */
@Data
@AllArgsConstructor
public class CreateCategoryDto {
    /**
     * name string
     */
    @NonNull
    private String name;
}
