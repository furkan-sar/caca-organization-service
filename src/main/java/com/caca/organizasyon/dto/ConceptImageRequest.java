package com.caca.organizasyon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConceptImageRequest {

    private int index;

    @NotNull(message = "Concept seçilmelidir")
    private Long conceptId;

    @NotNull(message = "Image seçilmelidir")
    private Long imageId;
}
