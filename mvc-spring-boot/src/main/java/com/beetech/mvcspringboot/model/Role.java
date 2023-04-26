package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true)
    @NonNull
    @Getter
    private String name;
}
