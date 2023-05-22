package com.beetech.mvcspringboot.model;

import com.beetech.mvcspringboot.utils.DateTimeFormatterUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products = new LinkedHashSet<>();

    public Category() {
    }

    public String getCreatedString() {
        return DateTimeFormatterUtil.formatLocalDateTime(this.created);
    }

    public String getUpdatedString() {
        return DateTimeFormatterUtil.formatLocalDateTime(this.updated);
    }
}