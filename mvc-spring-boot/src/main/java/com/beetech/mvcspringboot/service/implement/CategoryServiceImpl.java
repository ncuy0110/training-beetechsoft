package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Category;
import com.beetech.mvcspringboot.repository.CategoryRepository;
import com.beetech.mvcspringboot.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

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
