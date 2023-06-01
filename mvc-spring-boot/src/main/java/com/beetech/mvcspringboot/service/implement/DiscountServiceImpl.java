package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.admin.discount.dto.CreateDiscountDto;
import com.beetech.mvcspringboot.model.*;
import com.beetech.mvcspringboot.repository.DiscountRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductService productService;
    private final CartService cartService;


    @Override
    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Override
    public void save(CreateDiscountDto dto) {
        Discount discount = Discount.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .percent(dto.getPercent())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
        discount.addProduct(productService.findOne(dto.getProductIdA()));
        discount.addProduct(productService.findOne(dto.getProductIdB()));
        discountRepository.save(discount);
    }

    @Override
    public List<Discount> findAllCanApplyNow() {
        LocalDateTime now = LocalDateTime.now();
        return discountRepository.findAllByStartTimeBeforeAndEndTimeAfter(now, now);
    }

    @Override
    public List<Discount> findAllByUserCart(Long userId) {
        return discountRepository.findDiscountsByCart(userId, LocalDateTime.now());
    }

    @Override
    public Double getChangedAmount(Long userId, Long discountId) {
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        if (optionalDiscount.isEmpty()) {
            throw new EntityNotFoundException("Discount not found!");
        }
        Discount discount = optionalDiscount.get();
        Double totalAmount = cartService.getTotalByUserId(userId);
        Product productA = discount.getProducts().get(0);
        Product productB = discount.getProducts().get(1);

        CartItem cartItemOfProductA = cartService.findOneByUserAndProduct(userId, productA.getId());
        CartItem cartItemOfProductB = cartService.findOneByUserAndProduct(userId, productB.getId());

        Long quantity = cartItemOfProductA.getQuantity() < cartItemOfProductB.getQuantity()
                ? cartItemOfProductA.getQuantity() : cartItemOfProductB.getQuantity();

        Double discountAmount = (productA.getPrice() + productB.getPrice()) * quantity * discount.getPercent() / 100;

        return totalAmount - discountAmount;
    }


    @Override
    public Order getChangedAmount(Order order, Long discountId) {
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        if (optionalDiscount.isEmpty()) {
            throw new EntityNotFoundException("Discount not found!");
        }
        Discount discount = optionalDiscount.get();
        Double totalAmount = order.getTotal();
        Product productA = discount.getProducts().get(0);
        Product productB = discount.getProducts().get(1);

        OrderDetail orderDetailA = order.findOrderDetailByProductId(productA.getId());
        OrderDetail orderDetailB = order.findOrderDetailByProductId(productB.getId());

        Long quantity = orderDetailA.getQuantity() < orderDetailB.getQuantity()
                ? orderDetailA.getQuantity() : orderDetailB.getQuantity();

        Double discountAmount = (orderDetailA.getPrice() + orderDetailB.getPrice()) * quantity * discount.getPercent() / 100;
        order.setDiscount(discount);
        order.setTotalAmountAfterDiscount(totalAmount - discountAmount);

        return order;
    }

}
