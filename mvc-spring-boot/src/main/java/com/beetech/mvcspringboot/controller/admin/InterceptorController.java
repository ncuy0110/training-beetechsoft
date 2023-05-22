package com.beetech.mvcspringboot.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interceptor")
public class InterceptorController {
    @GetMapping("")
    public String demoInterceptor() {
        return "Hello world";
    }
}
