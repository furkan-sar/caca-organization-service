package com.caca.organizasyon.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.caca.organizasyon.config.AppProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.exception.ServerException;

@Service
public class FileStorageService {


    private final Path fileStoragePath;

    public FileStorageService(AppProperties appProperties) {
        this.fileStoragePath = Paths.get(appProperties.getUploadDir()).toAbsolutePath().normalize();
        createUploadDirectory();
    }

    private void createUploadDirectory() {
        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw ServerException.of(ErrorCode.FILE_COULD_NOT_SAVED);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String fileName = generateUniqueFileName(file);
            Path targetLocation = fileStoragePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return targetLocation.toString();
        } catch (IOException e) {
            throw ServerException.of(ErrorCode.FILE_COULD_NOT_SAVED);
        }
    }

    public void deleteFile(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            throw ServerException.of(ErrorCode.FILE_COULD_NOT_DELETED);
        }
    }

    private String generateUniqueFileName(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }
}
