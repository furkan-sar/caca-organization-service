package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

	@NotBlank(message = "Ürün adı boş olamaz")
	private String name;

	private String description;

	@NotNull(message = "Kategori seçilmelidir")
	private Long categoryId;

}