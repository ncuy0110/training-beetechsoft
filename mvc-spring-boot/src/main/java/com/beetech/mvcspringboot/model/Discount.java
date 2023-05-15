package com.beetech.mvcspringboot.model;

import com.beetech.mvcspringboot.utils.DateTimeFormatterUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discount")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "discount_product",
            joinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    @Column
    private Byte percent;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public String getStartTime() {
        return DateTimeFormatterUtil.formatLocalDateTime(this.startTime);
    }

    public String getEndTime() {
        return DateTimeFormatterUtil.formatLocalDateTime(this.endTime);
    }

    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    @Override
    public String toString() {
        return getName() + ": " + getProducts().get(0).getName() + "+" + getProducts().get(1).getName() + "-----" + getPercent() + "%";
    }
}
