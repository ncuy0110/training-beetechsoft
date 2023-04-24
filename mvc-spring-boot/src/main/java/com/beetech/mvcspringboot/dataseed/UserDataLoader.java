package com.beetech.mvcspringboot.dataseed;

import com.beetech.mvcspringboot.constants.RoleConstant;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The type User data loader.
 */
@Component
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User data loader.
     *
     * @param userRepository  the user repository
     * @param roleRepository  the role repository
     * @param passwordEncoder the password encoder
     */
    public UserDataLoader(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findUserByUsername("admin").isEmpty()) {
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.addRole(roleRepository.findRoleByName(RoleConstant.NORMAL));
            user.addRole(roleRepository.findRoleByName(RoleConstant.ADMIN));
            userRepository.save(user);
        }
    }
}
