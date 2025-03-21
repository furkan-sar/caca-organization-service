package com.caca.organizasyon.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductResponse {

	private Long id;

	private String name;

	private String description;

	private CategoryResponse category;

	private List<ProductImageResponse> images;

}