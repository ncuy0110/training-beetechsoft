package com.beetech.mvcspringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * The type Cart keypair.
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CartKeypair implements Serializable {
    /**
     * Serial id
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * user id
     */
    @Column(name = "user_id")
    private Long userid;

    /**
     * product id
     */
    @Column(name = "product_id")
    private Long productId;
}
