package com.wwx.teamall.controller;

import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public Result getAll() {
        return categoryService.getAll();
    }
}
