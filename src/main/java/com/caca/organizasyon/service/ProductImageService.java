package com.caca.organizasyon.service;

import com.caca.organizasyon.dto.ProductImageRequest;
import com.caca.organizasyon.dto.ProductImageResponse;

import java.util.List;

public interface ProductImageService {

	ProductImageResponse save(ProductImageRequest request);

	ProductImageResponse findById(Long id);

	List<ProductImageResponse> findImagesByProductId(Long productId);

	void deleteById(Long id);

}
