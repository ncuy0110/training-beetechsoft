package com.beetech.mvcspringboot.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Interceptor controller.
 */
@RestController
@RequestMapping("/api/v1/interceptor")
public class InterceptorController {
    /**
     * Demo interceptor string.
     *
     * @return the string
     */
    @GetMapping("")
    public String demoInterceptor() {
        return "Hello world";
    }
}
