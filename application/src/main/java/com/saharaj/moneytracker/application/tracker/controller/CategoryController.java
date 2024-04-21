package com.saharaj.moneytracker.application.tracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saharaj.moneytracker.application.tracker.model.Category;
import com.saharaj.moneytracker.application.tracker.repository.CategoryRepository;

@RestController
@RequestMapping("category")
public class CategoryController {
    CategoryRepository categoryRepository;

    CategoryController (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/add")
    public Category addCategory (@RequestBody Category category) {
        categoryRepository.save(category);
        return category;
    }
    
}
