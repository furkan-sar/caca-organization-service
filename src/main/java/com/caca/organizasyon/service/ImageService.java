package com.caca.organizasyon.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.caca.organizasyon.dto.ImageResponse;

public interface ImageService {

    ImageResponse save(MultipartFile file);

    List<ImageResponse> findAll(boolean includeBase64);

    ImageResponse findById(Long id, boolean includeBase64);

    void deleteById(Long id);

}
