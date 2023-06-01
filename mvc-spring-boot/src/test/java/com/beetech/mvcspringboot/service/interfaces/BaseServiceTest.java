package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.model.Category;
import com.beetech.mvcspringboot.model.Role;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.repository.CategoryRepository;
import com.beetech.mvcspringboot.repository.RoleRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.implement.ProductServiceImpl;
import com.beetech.mvcspringboot.service.implement.UserServiceImpl;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;


@Getter
@Setter
@ExtendWith(MockitoExtension.class)
public class BaseServiceTest {

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    CustomPasswordEncoder passwordEncoder;
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    ProductServiceImpl productService;

    private List<User> users;
    String username = "ncuy0110";
    String password = "uy2444";

    @Mock
    List<Category> categories;

    @BeforeEach
    void setUp() {
        openMocks(this);
        setUpCategoryRepository();
        roleRepository = mock(RoleRepository.class);
        Role roleAdmin = Role.builder()
                .name(RoleEnum.ADMIN)
                .id(1)
                .build();
        Role roleNormal = Role.builder()
                .name(RoleEnum.NORMAL)
                .id(2)
                .build();
        lenient().when(roleRepository.findRoleByName(RoleEnum.NORMAL)).thenReturn(roleNormal);
        lenient().when(roleRepository.findRoleByName(RoleEnum.ADMIN)).thenReturn(roleAdmin);

        users = new ArrayList<>(
                List.of(User.builder()
                        .username(username).password(passwordEncoder.encode(password))
                        .roles(new HashSet<>(
                                List.of(roleRepository.findRoleByName(RoleEnum.NORMAL), roleRepository.findRoleByName(RoleEnum.ADMIN))))
                        .build()));

        lenient().when(userRepository.save(any(User.class))).then(new Answer<User>() {
            long sequence = 1;

            @Override
            public User answer(InvocationOnMock invocationOnMock) {
                User user = invocationOnMock.getArgument(0);
                user.setId(sequence++);
                users.add(user);
                return user;
            }
        });

        lenient().when(userRepository.findUserByUsername(any(String.class))).then((Answer<Optional<User>>) invocationOnMock -> {
            String usernameParam = invocationOnMock.getArgument(0);
            return users.stream()
                    .filter(user -> user.getUsername().equals(usernameParam))
                    .findFirst();
        });

//        userRepository.save(User.builder()
//                .username("test_username")
//                .password(passwordEncoder.encode("test_password"))
//                .roles(new HashSet<>(List.of(roleRepository.findRoleByName(RoleEnum.NORMAL))))
//                .build());
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    void setUpCategoryRepository() {
        categoryRepository = mock(CategoryRepository.class);
        categories = new ArrayList<>();

        lenient().when(categoryRepository.findAll()).thenReturn(categories);
        lenient().when(categoryRepository.save(any(Category.class))).then(new Answer<>() {
            long sequence = 1;

            @Override
            public Object answer(InvocationOnMock invocationOnMock) {
                Category category = invocationOnMock.getArgument(0);
                category.setId(sequence++);
                categories.add(category);
                return null;
            }
        });

        categoryRepository.save(new Category("Category 1"));
        categoryRepository.save(new Category("Category 2"));
        lenient().when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(categories.get(0)));
    }
}
