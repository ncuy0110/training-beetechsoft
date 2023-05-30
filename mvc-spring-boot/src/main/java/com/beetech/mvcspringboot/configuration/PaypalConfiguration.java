package com.beetech.mvcspringboot.configuration;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type PayPal configuration.
 */
@Configuration
public class PaypalConfiguration {
    /**
     * client id from paypal dev
     */
    @Value("${paypal.client.id}")
    private String clientId;

    /**
     * client secret from PayPal dev
     */
    @Value("${paypal.client.secret}")
    private String clientSecret;

    /**
     * mode paypal
     */
    @Value("${paypal.mode}")
    private String mode;

    /**
     * Api context api context.
     *
     * @return the api context
     */
    @Bean
    public APIContext apiContext() {
        return new APIContext(this.clientId, this.clientSecret, this.mode);
    }
}
