package com.beetech.mvcspringboot.configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Thyme leaf configuration.
 */
@Configuration
public class ThymeLeafConfiguration {
    /**
     * Layout dialect layout dialect.
     *
     * @return the layout dialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
