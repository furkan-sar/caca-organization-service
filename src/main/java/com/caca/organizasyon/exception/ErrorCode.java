package com.caca.organizasyon.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    GENERAL_EXCEPTION("GENERAL_EXCEPTION", "Beklenmeyen bir durum oluştu."),
    BAD_REQUEST("BAD_REQUEST", "Geçersiz istek."),
    FILE_COULD_NOT_SAVED("FILE_COULD_NOT_SAVED", "Dosya yüklenemedi."),
    FILE_COULD_NOT_READ("FILE_COULD_NOT_READ", "Dosya okunamadı."),
    FILE_COULD_NOT_DELETED("FILE_COULD_NOT_DELETED", "Dosya silinemedi."),
    DUPLICATE_RECORD_EXIST("DUPLICATE_RECORD_EXIST", "Aynı değerde kayıt bulunmaktadır."),
    USER_NOT_FOUND("USER_NOT_FOUND", "Kullanıcı bulunamadı."),
    USER_NOT_ADMIN("USER_NOT_ADMIN", "Kullanıcı admin değil."),
    USER_ALREADY_ADMIN("USER_ALREADY_ADMIN", "Kullanıcı zaten admin."),
    USER_NOT_APPROVED("USER_NOT_APPROVED", "Hesap onaylanmamıştır."),
    USER_ALREADY_APPROVED("USER_ALREADY_APPROVED", "Hesap zaten onaylanmıştır."),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Kategori bulunamadı."),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "Ürün bulunamadı."),
    CONCEPT_NOT_FOUND("CONCEPT_NOT_FOUND", "Konsept bulunamadı."),
    IMAGE_NOT_FOUND("IMAGE_NOT_FOUND", "Resim bulunamadı."),
    PRODUCT_IMAGE_NOT_FOUND("PRODUCT_IMAGE_NOT_FOUND", "Ürün resmi bulunamadı."),
    DUPLICATE_PRODUCT_IMAGE_INDEX("DUPLICATE_PRODUCT_IMAGE_INDEX", "Aynı sırada ürün fotoğrafı bulunmaktadır."),
    CONCEPT_IMAGE_NOT_FOUND("CONCEPT_IMAGE_NOT_FOUND", "Konsept resmi bulunamadı."),
    DUPLICATE_CONCEPT_IMAGE_INDEX("DUPLICATE_CONCEPT_IMAGE_INDEX", "Aynı sırada konsept fotoğrafı bulunmaktadır."),
    TOKEN_IS_EXPIRED("TOKEN_IS_EXPIRED", "Oturum süreniz dolmuş."),
    INVALID_TOKEN("INVALID_TOKEN", "Geçersiz token."),
    BAD_CREDENTIALS("BAD_CREDENTIALS", "Kullanıcı adı ya da şifre yanlış."),
    UNAUTHORIZED("UNAUTHORIZED", "Giriş yapılmalıdır."),
    FORBIDDEN("FORBIDDEN", "Yetkisiz işlem."),
    USERNAME_ALREADY_TAKEN("USERNAME_ALREADY_TAKEN", "Bu kullanıcı adı zaten alınmış.");

    private final String code;

    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
