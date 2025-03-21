package com.caca.organizasyon.service;

import java.util.List;

import com.caca.organizasyon.dto.CategoryResponse;
import com.caca.organizasyon.dto.CategoryRequest;

public interface CategoryService {

    CategoryResponse save(CategoryRequest dtoCategoryIU);

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse update(Long id, CategoryRequest dtoCategoryIU);

    void deleteById(Long id);

}
