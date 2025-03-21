package com.caca.organizasyon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caca.organizasyon.entity.ProductImage;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query(value = "SELECT * FROM product_image WHERE product_id = :productId", nativeQuery = true)
    Optional<ProductImage> findImagesByProductId(@Param("productId") Long productId);

    boolean existsByIndex(Integer index);

}
