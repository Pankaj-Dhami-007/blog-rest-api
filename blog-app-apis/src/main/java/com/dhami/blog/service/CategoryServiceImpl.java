package com.dhami.blog.service;

import com.dhami.blog.entity.Category;
import com.dhami.blog.exceptions.ResourceNotFoundException;
import com.dhami.blog.payloads.CategoryDTO;
import com.dhami.blog.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
       Category existingCategory =  categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", id));
        existingCategory.setCategoryTitle(categoryDTO.getCategoryTitle());
        existingCategory.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long id) {
        Category existingCategory =  categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", id));
        categoryRepository.delete(existingCategory);
    }

    @Override
    public CategoryDTO getById(Long id) {
       Category categoryInDb =  categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category"," category Id",id));
        return modelMapper.map(categoryInDb,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTOS;
    }
}
