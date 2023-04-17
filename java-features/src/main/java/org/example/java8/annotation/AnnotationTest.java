package org.example.java8.annotation;

import jakarta.validation.*;

public class AnnotationTest {
    public static void main(String[] args) {
        TestBean test = new TestBean();
        test.a = null;
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        validator.validate(test).stream().forEach(AnnotationTest::printError);
        factory.close();
    }

    private static void printError(ConstraintViolation<TestBean> violation) {
        System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
    }
}
