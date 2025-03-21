package com.caca.organizasyon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caca.organizasyon.dto.CategoryResponse;
import com.caca.organizasyon.dto.ProductRequest;
import com.caca.organizasyon.dto.ProductResponse;
import com.caca.organizasyon.entity.Category;
import com.caca.organizasyon.entity.Product;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ResourceNotFoundException;
import com.caca.organizasyon.repository.CategoryRepository;
import com.caca.organizasyon.repository.ProductRepository;
import com.caca.organizasyon.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private ProductResponse toDto(Product product) {
        ProductResponse productResponse = new ProductResponse();

        BeanUtils.copyProperties(product, productResponse);
        CategoryResponse categoryResponse = new CategoryResponse();

        BeanUtils.copyProperties(product.getCategory(), categoryResponse);

        productResponse.setCategory(categoryResponse);

        return productResponse;
    }

    private Product toEntity(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        product.setCategory(category);

        return product;
    }

    @Override
    @Transactional
    public ProductResponse save(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = toEntity(request);
        product.setCategory(category);

        return toDto(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return toDto(productRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.PRODUCT_NOT_FOUND));

        updateProductFields(existingProduct, request);

        return toDto(productRepository.save(existingProduct));
    }

    private void updateProductFields(Product product, ProductRequest request) {
        Optional.ofNullable(request.getName())
                .filter(name -> !name.isEmpty())
                .ifPresent(product::setName);

        Optional.ofNullable(request.getDescription())
                .filter(desc -> !desc.isEmpty())
                .ifPresent(product::setDescription);

        Optional.ofNullable(request.getCategoryId())
                .ifPresent(categoryId -> {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CATEGORY_NOT_FOUND));
                    product.setCategory(category);
                });
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.delete(productRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.PRODUCT_NOT_FOUND)));
    }
}
