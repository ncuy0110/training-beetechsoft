package com.beetech.mvcspringboot.model;

import com.beetech.mvcspringboot.utils.DateTimeFormatterUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Category.
 */
@Getter
@Setter
@Entity
@Table(name = "category")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class Category implements Serializable {
    /**
     * Serial ID
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * name of category
     */
    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    /**
     * creation timestamp of category
     */
    @CreationTimestamp
    private LocalDateTime created;

    /**
     * update timestamp
     */
    @UpdateTimestamp
    private LocalDateTime updated;

    /**
     * list product of this category
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Product> products = new LinkedHashSet<>();


    /**
     * Gets created string.
     *
     * @return the created string
     */
    public String getCreatedString() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.created);
    }

    /**
     * Gets updated string.
     *
     * @return the updated string
     */
    public String getUpdatedString() {
        return DateTimeFormatterUtils.formatLocalDateTime(this.updated);
    }
}