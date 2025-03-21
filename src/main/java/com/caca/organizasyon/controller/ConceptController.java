package com.caca.organizasyon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caca.organizasyon.dto.ApiResponse;
import com.caca.organizasyon.dto.ConceptRequest;
import com.caca.organizasyon.dto.ConceptResponse;
import com.caca.organizasyon.service.ConceptService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/concept")
@RequiredArgsConstructor
public class ConceptController {

    private final ConceptService conceptService;

    @PostMapping
    public ResponseEntity<ApiResponse<ConceptResponse>> save(@Valid @RequestBody ConceptRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(conceptService.save(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConceptResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(conceptService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConceptResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(conceptService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConceptResponse>> update(@PathVariable Long id,
                                                               @RequestBody ConceptRequest request) {
        return ResponseEntity.ok(ApiResponse.success(conceptService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        conceptService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}