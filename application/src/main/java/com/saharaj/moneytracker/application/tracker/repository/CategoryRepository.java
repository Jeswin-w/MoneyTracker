package com.saharaj.moneytracker.application.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saharaj.moneytracker.application.tracker.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{
    
}
