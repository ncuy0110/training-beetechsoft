package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Discount repository.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    /**
     * Find all by start time before and end time after list.
     *
     * @param startTime the start time
     * @param endTime   the end time
     * @return the list
     */
    List<Discount> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Find discounts by cart list.
     *
     * @param userId the user id
     * @param now    the now
     * @return the list
     */
    @Query(value = "SELECT d FROM Discount d WHERE size(d.products) = "
            + "(SELECT count(c.product) FROM CartItem c WHERE c.user.id = :userId and c.quantity > 0 "
            + "AND c.product member of d.products) AND d.startTime < :now and d.endTime > :now")
    List<Discount> findDiscountsByCart(@Param("userId") Long userId, @Param("now") LocalDateTime now);

}
