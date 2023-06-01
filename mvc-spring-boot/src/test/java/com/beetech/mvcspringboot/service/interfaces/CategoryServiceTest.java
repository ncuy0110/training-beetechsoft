package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest extends BaseServiceTest {
    @InjectMocks
    CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        super.setUp();
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void findAll_ShouldReturnListCategory() {
        Assertions.assertEquals(categories.size(), categoryService.findAll().size());
    }

    @Test
    void create_WhenValidCategoryInfo() {
        var oldLengthOfCategories = categories.size();
        assertDoesNotThrow(() -> categoryService.create("Category test"));
        var newLengthOfCategories = categories.size();
        assertEquals(oldLengthOfCategories + 1, newLengthOfCategories);
    }
}