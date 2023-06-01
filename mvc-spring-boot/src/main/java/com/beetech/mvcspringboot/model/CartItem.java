package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Cart item.
 */
@Entity
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "cart")
public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * cart item id
     */
    @EmbeddedId
    @NonNull
    private CartKeypair id;

    /**
     * user own this cart item
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    /**
     * product in this cart item
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    /**
     * quantity of cart item
     */
    @Column(nullable = false)
    @NonNull
    private Long quantity;

    /**
     * creation timestamp
     */
    @CreationTimestamp
    private LocalDateTime created;

    /**
     * Update timestamp
     */
    @UpdateTimestamp
    private LocalDateTime updated;


    /**
     * Gets total.
     *
     * @return the total
     */
    public Double getTotal() {
        return getQuantity() * getProduct().getPrice();
    }
}
