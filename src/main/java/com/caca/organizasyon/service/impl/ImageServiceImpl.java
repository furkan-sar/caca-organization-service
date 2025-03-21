package com.caca.organizasyon.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.caca.organizasyon.dto.ImageResponse;
import com.caca.organizasyon.entity.Image;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ResourceNotFoundException;
import com.caca.organizasyon.exception.ServerException;
import com.caca.organizasyon.repository.ImageRepository;
import com.caca.organizasyon.service.FileStorageService;
import com.caca.organizasyon.service.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    private ImageResponse imageToDto(Image image, boolean includeBase64) {
        ImageResponse imageResponse = new ImageResponse();
        BeanUtils.copyProperties(image, imageResponse);

        if (includeBase64) {
            imageResponse.setBase64(getImageAsBase64(image.getImageUrl()));
        }

        return imageResponse;
    }

    private String getImageAsBase64(String imagePath) {
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(imagePath)));
        } catch (IOException e) {
            throw ServerException.of(ErrorCode.FILE_COULD_NOT_READ);
        }
    }

    @Transactional
    @Override
    public ImageResponse save(MultipartFile file) {
        Image image = new Image();
        image.setImageUrl(fileStorageService.storeFile(file));

        return imageToDto(imageRepository.save(image), false);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ImageResponse> findAll(boolean includeBase64) {
        return imageRepository.findAll().stream().map(image -> imageToDto(image, includeBase64)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ImageResponse findById(Long id, boolean includeBase64) {
        return imageToDto(
                imageRepository.findById(id).orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.IMAGE_NOT_FOUND)),
                includeBase64);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        fileStorageService.deleteFile(imageRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of(ErrorCode.IMAGE_NOT_FOUND)).getImageUrl());

        imageRepository.deleteById(id);
    }

}
