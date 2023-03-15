package com.example.employee.service;

import com.example.employee.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findCategoryById(Long id);
    void createCategory(Category category);
    Boolean updateCategoryById(Category category);
    Boolean deleteCategoryId(Long id);
}
