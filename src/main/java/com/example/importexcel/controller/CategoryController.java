package com.example.importexcel.controller;

import com.example.importexcel.model.Category;
import com.example.importexcel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public Category addCategory(@RequestBody Category category) {
        var category1 = categoryService.create(category);
        return category1;
    }
}
