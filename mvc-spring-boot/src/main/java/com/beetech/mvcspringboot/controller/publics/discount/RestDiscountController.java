package com.beetech.mvcspringboot.controller.publics.discount;

import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class RestDiscountController {
    private final DiscountService discountService;

    @GetMapping("/{discountId}/apply")
    public ResponseEntity<Double> applyDiscountForCart(@PathVariable String discountId, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(discountService.getChangedAmount(user.getId(), Long.parseLong(discountId)));
    }
}
