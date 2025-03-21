package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConceptProductRequest {

    @NotNull(message = "Product ID boş olamaz")
    private Long productId;

    @NotNull(message = "Adet boş olamaz")
    private int quantity;

}