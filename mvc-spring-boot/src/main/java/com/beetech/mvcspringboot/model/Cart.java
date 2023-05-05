package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Cart {
    @EmbeddedId
    @NonNull
    private CartKeypair id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @Column(nullable = false)
    @NonNull
    private Long quantity;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public Cart() {

    }
}
