package com.beetech.mvcspringboot.dataseed;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            user.addRole(roleRepository.findRoleByName(RoleEnum.ADMIN));
            user.addRole(roleRepository.findRoleByName(RoleEnum.NORMAL));
            userRepository.save(user);
        }
    }
}
