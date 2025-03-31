package com.dhami.blog.service;
import com.dhami.blog.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    //create

     CategoryDTO createCategory(CategoryDTO categoryDTO);
    // update
     CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);
    //delete
     void deleteCategory(Long id);

    // get
     CategoryDTO getById(Long id);

    //getAll
     List<CategoryDTO> getAll();
}
