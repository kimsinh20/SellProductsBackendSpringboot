package com.example.employee.service.Impl;


import com.example.employee.model.Category;
import com.example.employee.respository.CategoryRepository;
import com.example.employee.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        Optional<Category> rs = categoryRepository.findById(id);
        return rs;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Boolean updateCategoryById(Category category) {
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Boolean deleteCategoryId(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
