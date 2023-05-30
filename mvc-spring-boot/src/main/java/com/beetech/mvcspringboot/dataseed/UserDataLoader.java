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
    /**
     * inject user repository
     */
    private final UserRepository userRepository;

    /**
     * inject role repository
     */
    private final RoleRepository roleRepository;

    /**
     * inject custom password encoder
     */
    private final CustomPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String username = "admin";
        if (this.userRepository.findUserByUsername(username).isEmpty()) {
            User user = new User(username, this.passwordEncoder.encode("admin"));
            user.addRole(this.roleRepository.findRoleByName(RoleEnum.ADMIN));
            user.addRole(this.roleRepository.findRoleByName(RoleEnum.NORMAL));
            this.userRepository.save(user);
        }
    }
}
