package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.service.interfaces.OrderDetailService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Long userId) {

    }
}
