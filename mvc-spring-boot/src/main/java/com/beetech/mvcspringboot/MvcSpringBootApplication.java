package com.beetech.mvcspringboot;

import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Mvc spring boot application.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class MvcSpringBootApplication implements CommandLineRunner {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MvcSpringBootApplication.class);
    /**
     * file store service for init upload folder
     */
    private final FilesStorageService storageService;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MvcSpringBootApplication.class, args);
    }

    @Override
    public void run(String... arg) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("init upload folder");
        }
        storageService.init();
    }
}
