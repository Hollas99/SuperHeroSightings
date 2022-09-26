package com.superhero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superhero.model.Sighting;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long>{
	Optional<Sighting> findById(Long id);
	Sighting findByName(String name);
}
