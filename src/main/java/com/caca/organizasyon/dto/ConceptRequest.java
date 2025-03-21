package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConceptRequest {

    @NotBlank(message = "Isim boş olamaz")
    private String name;

}