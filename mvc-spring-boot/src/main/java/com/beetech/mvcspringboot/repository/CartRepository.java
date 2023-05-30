package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.CartItem;
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
public interface CartRepository extends JpaRepository<CartItem, CartKeypair> {
    @Query("SELECT c from CartItem c where c.user.id = :userId and c.quantity > 0")
    List<CartItem> findAllByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query("UPDATE CartItem c set c.quantity = 0 where c.user.id = :userId")
    void resetCartByUserId(Long userId);
}
