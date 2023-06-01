package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Order detail.
 */
@Entity
@Table(name = "order_detail")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false, updatable = false)
    private Long quantity;

    @Column(nullable = false, updatable = false)
    private Double price;

    @Column(nullable = false, updatable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
