package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    List<Product> findAllByIdIn(List<Long> ids);
}
