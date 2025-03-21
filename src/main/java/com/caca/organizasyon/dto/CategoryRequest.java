package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank(message = "Kategori adı boş olamaz")
    private String name;
}
