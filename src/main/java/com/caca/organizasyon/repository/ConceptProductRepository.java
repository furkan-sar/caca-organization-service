package com.caca.organizasyon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caca.organizasyon.entity.ConceptProduct;

@Repository
public interface ConceptProductRepository extends JpaRepository<ConceptProduct, Long> {

}
