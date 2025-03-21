package com.caca.organizasyon.service.impl;

import com.caca.organizasyon.exception.BusinessException;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.caca.organizasyon.dto.ProductImageRequest;
import com.caca.organizasyon.dto.ProductImageResponse;
import com.caca.organizasyon.entity.ProductImage;
import com.caca.organizasyon.repository.ProductImageRepository;
import com.caca.organizasyon.service.ProductImageService;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    private ProductImageResponse toDto(ProductImage productImage) {
        ProductImageResponse productImageResponse = new ProductImageResponse();
        BeanUtils.copyProperties(productImage, productImageResponse);

        return productImageResponse;
    }

    private ProductImage toEntity(ProductImageRequest request) {
        ProductImage productImage = new ProductImage();
        BeanUtils.copyProperties(request, productImage);

        return productImage;
    }

    private boolean existsByIndex(Integer index) {
        return productImageRepository.existsByIndex(index);
    }

    @Override
    @Transactional
    public ProductImageResponse save(ProductImageRequest request) {
        if (existsByIndex(request.getIndex())) {
            throw BusinessException.of(ErrorCode.DUPLICATE_PRODUCT_IMAGE_INDEX);
        }

        return toDto(productImageRepository.save(toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImageResponse findById(Long id) {
        return toDto(productImageRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.PRODUCT_IMAGE_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageResponse> findImagesByProductId(Long productId) {
        Optional<ProductImage> images = productImageRepository.findImagesByProductId(productId);

        if (images.isEmpty()) {
            throw ResourceNotFoundException.of(ErrorCode.PRODUCT_IMAGE_NOT_FOUND);
        }

        return images.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productImageRepository.delete(productImageRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.PRODUCT_IMAGE_NOT_FOUND)));
    }


}
