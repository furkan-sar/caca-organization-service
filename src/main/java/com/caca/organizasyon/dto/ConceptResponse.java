package com.caca.organizasyon.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ConceptResponse {

    private Long id;

    private String name;

    private Set<ConceptProductResponse> products;

    private Set<ConceptImageResponse> images;

}
