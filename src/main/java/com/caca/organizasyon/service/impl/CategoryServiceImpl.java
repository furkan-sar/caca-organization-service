package com.caca.organizasyon.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caca.organizasyon.dto.CategoryRequest;
import com.caca.organizasyon.dto.CategoryResponse;
import com.caca.organizasyon.entity.Category;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ResourceNotFoundException;
import com.caca.organizasyon.repository.CategoryRepository;
import com.caca.organizasyon.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryResponse categoryToDto(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryResponse);

        return categoryResponse;
    }

    private Category dtoToCategory(CategoryRequest request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category);

        return category;
    }

    @Override
    @Transactional
    public CategoryResponse save(CategoryRequest request) {
        return categoryToDto(categoryRepository.save(dtoToCategory(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(this::categoryToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return categoryToDto(categoryRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND)));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category dbCategory = categoryRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            dbCategory.setName(request.getName());
        }

        return categoryToDto(categoryRepository.save(dbCategory));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.delete(categoryRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND)));
    }

}
