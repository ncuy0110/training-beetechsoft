package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Category;
import com.beetech.mvcspringboot.repository.CategoryRepository;
import com.beetech.mvcspringboot.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void create(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);
    }
}
