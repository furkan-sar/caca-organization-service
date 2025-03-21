package com.caca.organizasyon.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.caca.organizasyon.dto.ApiResponse;
import com.caca.organizasyon.dto.ImageResponse;
import com.caca.organizasyon.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<ImageResponse>> save(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(ApiResponse.success(imageService.save(file)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ImageResponse>>> findAll(
            @RequestParam(name = "includeBase64", defaultValue = "false") boolean includeBase64) {
        return ResponseEntity.ok(ApiResponse.success(imageService.findAll(includeBase64)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ImageResponse>> findById(@PathVariable Long id,
                                                               @RequestParam(name = "includeBase64", defaultValue = "false") boolean includeBase64) {
        return ResponseEntity.ok(ApiResponse.success(imageService.findById(id, includeBase64)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        imageService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}