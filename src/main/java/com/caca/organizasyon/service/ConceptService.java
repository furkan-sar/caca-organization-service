package com.caca.organizasyon.service;

import java.util.List;

import com.caca.organizasyon.dto.ConceptRequest;
import com.caca.organizasyon.dto.ConceptResponse;

public interface ConceptService {

    ConceptResponse save(ConceptRequest request);

    List<ConceptResponse> findAll();

    ConceptResponse findById(Long id);

    ConceptResponse update(Long id, ConceptRequest request);

    void deleteById(Long id);

}
