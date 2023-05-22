package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.CartKeypair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartKeypair> {
    List<Cart> findAllByUserIdAndQuantityGreaterThan(Long userId, Long quantity);
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query("UPDATE Cart c set c.quantity = 0 where c.user.id = :userId")
    void resetCartByUserId(Long userId);
}
