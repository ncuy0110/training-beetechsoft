package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.interfaces.UserService;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomPasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterDto registerDto) {
        User user = new User(registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()));
        user.addRole(roleRepository.findRoleByName(RoleEnum.NORMAL));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return optionalUser.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        user.setRoles(new HashSet<>());
        userRepository.delete(user);
    }

}
