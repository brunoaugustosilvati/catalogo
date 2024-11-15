package com.catalogo.service;

import com.catalogo.domain.category.Category;
import com.catalogo.domain.category.CategoryDTO;
import com.catalogo.domain.category.exception.CategororyNotFoundException;
import com.catalogo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category insert(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO);
        this.repository.save(category);
        return category;
    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.repository.findById(id).orElseThrow(CategororyNotFoundException::new);
        if(!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if(!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());
        this.repository.save(category);
        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id).orElseThrow(CategororyNotFoundException::new);
        this.repository.delete(category);
    }
}
