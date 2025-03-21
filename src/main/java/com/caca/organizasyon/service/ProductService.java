package com.caca.organizasyon.service;

import java.util.List;

import com.caca.organizasyon.dto.ProductRequest;
import com.caca.organizasyon.dto.ProductResponse;

public interface ProductService {

	ProductResponse save(ProductRequest request);
	
	List<ProductResponse> findAll();

	ProductResponse findById(Long id);

	ProductResponse update(Long id, ProductRequest request);

	void deleteById(Long id);
}