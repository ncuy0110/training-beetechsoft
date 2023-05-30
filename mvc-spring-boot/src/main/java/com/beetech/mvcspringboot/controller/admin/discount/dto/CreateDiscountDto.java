package com.beetech.mvcspringboot.controller.admin.discount.dto;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * The type Create discount dto.
 */
@Data
@AllArgsConstructor
@ToString
public class CreateDiscountDto {
    /**
     * name string
     */
    private String name;
    /**
     * description string
     */
    private String description;

    /**
     * percent Byte (1-70)
     */
    @Min(1)
    @Max(70)
    private Byte percent;

    /**
     * product id A
     */
    @NonNull
    private Long productIdA;

    /**
     * product id A
     */
    @NonNull
    private Long productIdB;

    /**
     * start time
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startTime;

    /**
     * end time
     */
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime endTime;

    private boolean isValidEndTime() {
        return getEndTime().isAfter(getStartTime());
    }

    private boolean isValidProductId() {
        return !productIdA.equals(productIdB);
    }

    /**
     * Validate.
     */
    public void validate() {
        if (!isValidProductId()) {
            throw new ValidationException("Different product is required!");
        }
        if (!isValidEndTime()) {
            throw new ValidationException("End time cannot before start time!");
        }
    }
}
