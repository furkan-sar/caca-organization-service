package com.caca.organizasyon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caca.organizasyon.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
