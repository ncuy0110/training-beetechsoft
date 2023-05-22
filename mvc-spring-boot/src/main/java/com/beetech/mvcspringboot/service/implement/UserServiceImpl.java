package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.interfaces.UserService;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;


    /**
     * Instantiates a new User service.
     *
     * @param userRepository  the user repository
     * @param roleRepository  the role repository
     * @param passwordEncoder the password encoder
     */
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User save(RegisterDto registerDto) {
        User user = new User(registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()));
        user.addRole(roleRepository.findRoleByName(RoleEnum.NORMAL));
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            System.out.println("hello");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return optionalUser.get();
    }

}
