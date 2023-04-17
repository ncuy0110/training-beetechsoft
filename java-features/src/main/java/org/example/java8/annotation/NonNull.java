package org.example.java8.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Documented
@Constraint(validatedBy = NonNull.Validator.class)
public @interface NonNull {
    String message() default "string must be not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NonNull, String> {
        String message;

        @Override
        public void initialize(NonNull nonNull) {
            this.message = nonNull.message();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value != null;
        }
    }
}
