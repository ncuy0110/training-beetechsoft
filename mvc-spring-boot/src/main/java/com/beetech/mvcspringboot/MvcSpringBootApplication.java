package com.beetech.mvcspringboot;

import com.beetech.mvcspringboot.service.interfaces.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Mvc spring boot application.
 */
@SpringBootApplication
public class MvcSpringBootApplication implements CommandLineRunner {
    private final FilesStorageService storageService;

    public MvcSpringBootApplication(FilesStorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MvcSpringBootApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        System.out.println("init upload folder");
        storageService.init();
    }
}
