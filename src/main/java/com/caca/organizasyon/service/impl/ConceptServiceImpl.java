package com.caca.organizasyon.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caca.organizasyon.dto.ConceptRequest;
import com.caca.organizasyon.dto.ConceptResponse;
import com.caca.organizasyon.entity.Concept;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ResourceNotFoundException;
import com.caca.organizasyon.repository.ConceptRepository;
import com.caca.organizasyon.service.ConceptService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConceptServiceImpl implements ConceptService {

    private final ConceptRepository conceptRepository;

    private ConceptResponse conceptToDto(Concept concept) {
        ConceptResponse conceptResponse = new ConceptResponse();
        BeanUtils.copyProperties(concept, conceptResponse);

        return conceptResponse;
    }

    private Concept dtoToConcept(ConceptRequest request) {
        Concept concept = new Concept();
        BeanUtils.copyProperties(request, concept);

        return concept;
    }

    @Override
    @Transactional
    public ConceptResponse save(ConceptRequest request) {
        return conceptToDto(conceptRepository.save(dtoToConcept(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConceptResponse> findAll() {
        return conceptRepository.findAll().stream().map(this::conceptToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ConceptResponse findById(Long id) {
        return conceptToDto(conceptRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CONCEPT_NOT_FOUND)));
    }

    @Override
    @Transactional
    public ConceptResponse update(Long id, ConceptRequest request) {
        Concept dbConcept = conceptRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CONCEPT_NOT_FOUND));

        if (request.getName() != null && !request.getName().isEmpty()) {
            dbConcept.setName(request.getName());
        }

        return conceptToDto(conceptRepository.save(dbConcept));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        conceptRepository.delete(conceptRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.CONCEPT_NOT_FOUND)));
    }

}
