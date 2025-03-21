package com.caca.organizasyon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caca.organizasyon.entity.Concept;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long> {
	
	Optional<Concept> findByName(String name);
	
}
