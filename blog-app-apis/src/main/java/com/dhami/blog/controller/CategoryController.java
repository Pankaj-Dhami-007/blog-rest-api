package com.dhami.blog.controller;

import com.dhami.blog.payloads.ApiResponse;
import com.dhami.blog.payloads.CategoryDTO;
import com.dhami.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // create
    // update
    //delete
    // get by id
    // gel all

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
             CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
             return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category is deleted successfully ", true), HttpStatus.OK);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        CategoryDTO category = categoryService.getById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }


}
