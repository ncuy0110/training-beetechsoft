package com.beetech.mvcspringboot.dataseed;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The type User data loader.
 */
@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findUserByUsername("admin").isEmpty()) {
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.addRole(roleRepository.findRoleByName(RoleEnum.ADMIN));
            user.addRole(roleRepository.findRoleByName(RoleEnum.NORMAL));
            userRepository.save(user);
        }
    }
}
