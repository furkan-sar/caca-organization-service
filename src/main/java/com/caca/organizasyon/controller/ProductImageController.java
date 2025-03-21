package com.caca.organizasyon.controller;

import com.caca.organizasyon.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.caca.organizasyon.service.ProductImageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/product-image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductImageResponse>> save(
            @Valid @RequestBody ProductImageRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productImageService.save(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductImageResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productImageService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productImageService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}