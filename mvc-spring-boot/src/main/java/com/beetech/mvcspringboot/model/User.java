package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, length = 40)
    @NonNull
    private String username;

    @Column
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @NonNull
    @Getter
    private Collection<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public @NonNull String getPassword() {
        return password;
    }

    @Override
    public @NonNull String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
