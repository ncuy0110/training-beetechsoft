package com.beetech.mvcspringboot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "product_image")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProductImage extends FileAttachment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImage(String filename, String originalName, String contentType, Product product) {
        setPath(filename);
        setFileType(contentType);
        setProduct(product);
        setOriginalName(originalName);
    }

}
