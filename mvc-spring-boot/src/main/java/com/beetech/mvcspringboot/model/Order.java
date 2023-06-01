package com.beetech.mvcspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * The type Order.
 */
@Entity
@Table(name = "orders")
@Builder
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * order by user id
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * total amount in order
     */
    @Column(nullable = false, updatable = false)
    @NonNull
    private Double total;

    /**
     * total amount after apply discount
     */
    @Column(nullable = false)
    private Double totalAmountAfterDiscount;

    /**
     * is paid or not yet
     */
    @Column(columnDefinition = "boolean default false")
    private boolean isPaid;

    /**
     * paymentId if is paid
     */
    @Column
    private String paymentId;

    /**
     * payer Id
     */
    @Column
    private String payerId;

    /**
     * order detail for this order
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Collection<OrderDetail> orderDetails = new ArrayList<>();

    /**
     * discount id
     */
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    /**
     * Find order detail by product id order detail.
     *
     * @param productId the product id
     * @return the order detail
     */
    public OrderDetail findOrderDetailByProductId(Long productId) {
        List<OrderDetail> orders = this.orderDetails.stream().filter(orderDetail -> Objects.equals(orderDetail.getProduct().getId(), productId)).toList();
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }
}
