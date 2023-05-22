package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    @NonNull
    private Double total;

    @Column(nullable = false)
    private Double totalAmountAfterDiscount;

    @Column(columnDefinition = "boolean default false")
    private boolean isPaid;

    @Column
    private String paymentId;

    @Column
    private String payerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Collection<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public OrderDetail findOrderDetailByProductId(Long productId) {
        List<OrderDetail> orders = orderDetails.stream().filter(orderDetail -> orderDetail.getProduct().getId() == productId).toList();
        if (orders.size() == 0) {
            return null;
        }
        return orders.get(0);
    }
}
