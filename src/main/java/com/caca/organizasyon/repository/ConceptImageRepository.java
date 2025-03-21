package com.caca.organizasyon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caca.organizasyon.entity.ConceptImage;

@Repository
public interface ConceptImageRepository extends JpaRepository<ConceptImage, Long> {

}