package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void create(String name);
}
