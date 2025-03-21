package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImageRequest {

	private int index;

	@NotNull(message = "Ürün seçilmelidir")
	private Long productId;

	@NotNull(message = "Resim seçilmelidir")
	private Long imageId;

}