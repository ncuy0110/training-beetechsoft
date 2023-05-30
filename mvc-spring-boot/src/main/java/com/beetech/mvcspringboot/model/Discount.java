package com.beetech.mvcspringboot.model;

import com.beetech.mvcspringboot.utils.DateTimeFormatterUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Discount.
 */
@Entity
@Table(name = "discount")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * discount id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * name of discount
     */
    @Column
    private String name;

    /**
     * description of discount
     */
    @Column
    private String description;

    /**
     * product required in discount
     */
    @ManyToMany
    @JoinTable(
            name = "discount_product",
            joinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    /**
     * percent discount
     */
    @Column
    private Byte percent;

    /**
     * start time to apply discount
     */
    @Column
    private LocalDateTime startTime;

    /**
     * end time to apply discount
     */
    @Column
    private LocalDateTime endTime;

    /**
     * creation time
     */
    @CreationTimestamp
    private LocalDateTime created;

    /**
     * update time
     */
    @UpdateTimestamp
    private LocalDateTime updated;

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.startTime);
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.endTime);
    }

    /**
     * Add product.
     *
     * @param product the product
     */
    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }

    @Override
    public String toString() {
        return getName() + ": " + getProducts().get(0).getName() + "+" + getProducts().get(1).getName() + "-----" + getPercent() + "%";
    }
}
