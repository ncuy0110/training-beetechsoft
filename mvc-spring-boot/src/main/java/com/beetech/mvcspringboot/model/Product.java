package com.beetech.mvcspringboot.model;

import com.beetech.mvcspringboot.utils.DateTimeFormatterUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@NoArgsConstructor(force = true)
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "price", nullable = false)
    @NonNull
    private Double price;

    @Column(name = "quantity", nullable = false)
    @NonNull
    private Long quantity;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @NonNull
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Collection<CartItem> cartItems = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private Set<Discount> discounts = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public String getCreatedString() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.created);
    }

    public String getUpdatedString() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.updated);
    }

    public String getListImageString() {
        return getImages().stream()
                .map(ProductImage::getPath)
                .collect(Collectors.joining("\n"));
    }

    public String getFirstImagePath() {
        return getImages().get(0).getPath();
    }
}