package com.beetech.mvcspringboot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "product_image")
@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class ProductImage extends FileAttachment {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @NonNull
    private Product product;

    public ProductImage(String filename, String contentType, Product product) {
        setPath(filename);
        setFileType(contentType);
        setProduct(product);
    }

}
