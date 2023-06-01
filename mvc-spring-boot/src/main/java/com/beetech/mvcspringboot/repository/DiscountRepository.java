package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime startTime, LocalDateTime endTime);

    // 2 products,
    // dem so san pham o trong gio hang, ma no nam trong list product cua discount
//    discoutn co spA, spB, cart: spA, spB, spC, - spA, spB cung nam trong discount =>
    @Query(value = "SELECT d FROM Discount d WHERE size(d.products) = "
            + "(SELECT count(c.product) FROM Cart c WHERE c.user.id = :userId and c.quantity > 0 "
            + "AND c.product member of d.products) AND d.startTime < :now and d.endTime > :now")
    List<Discount> findDiscountsByCart(@Param("userId") Long userId, @Param("now") LocalDateTime now);

}
