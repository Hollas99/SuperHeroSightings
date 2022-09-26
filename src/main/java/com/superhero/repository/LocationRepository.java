package com.superhero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superhero.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	Optional<Location> findById(Long id);
	Location findByName(String name);
}
