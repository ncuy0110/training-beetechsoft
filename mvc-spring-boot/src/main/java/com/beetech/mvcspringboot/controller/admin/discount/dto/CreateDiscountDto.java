package com.beetech.mvcspringboot.controller.admin.discount.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class CreateDiscountDto {
    private String name;
    private String description;
    @Min(1)
    @Max(70)
    private Byte percent;
    @NonNull
    private Long productIdA;
    @NonNull
    private Long productIdB;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endTime;

    private boolean isValidEndTime() {
        return getEndTime().isAfter(getStartTime());
    }

    private boolean isValidProductId() {
        return !productIdA.equals(productIdB);
    }

    public void validate() {
        if (!isValidProductId()) {
            throw new RuntimeException("Different product is required!");
        }
        if (!isValidEndTime()) {
            throw new RuntimeException("End time cannot before start time!");
        }
    }
}
