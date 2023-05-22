package com.beetech.mvcspringboot.dataseed;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.Role;
import com.beetech.mvcspringboot.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The type Role data loader.
 */
@Component
public class RoleDataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    /**
     * Instantiates a new Role data loader.
     *
     * @param roleRepository the role repository
     */
    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role admin = new Role(RoleEnum.ADMIN);
            Role normal = new Role(RoleEnum.NORMAL);
            roleRepository.save(admin);
            roleRepository.save(normal);
        } catch (Exception e) {
            System.out.println("roles existed!");
        }
    }
}
