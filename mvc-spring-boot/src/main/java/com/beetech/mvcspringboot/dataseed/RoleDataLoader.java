package com.beetech.mvcspringboot.dataseed;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.Role;
import com.beetech.mvcspringboot.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * The type Role data loader.
 */
@Component
@RequiredArgsConstructor
public class RoleDataLoader implements CommandLineRunner {
    /**
     * logger for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDataLoader.class);

    /**
     * inject role repository
     */
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        try {
            Role admin = new Role(RoleEnum.ADMIN);
            Role normal = new Role(RoleEnum.NORMAL);
            roleRepository.save(admin);
            roleRepository.save(normal);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("roles existed!");
            }
        }
    }
}
