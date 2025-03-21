package com.caca.organizasyon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "concept_images", uniqueConstraints = @UniqueConstraint(columnNames = {"concept_id", "image_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConceptImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int index;

    @ManyToOne
    private Concept concept;

    @ManyToOne
    private Image image;

}
