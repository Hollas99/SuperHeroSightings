package com.superhero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superhero.model.Super;



@Repository
public interface SuperRepository extends JpaRepository<Super, Long>{
	Optional<Super> findById(Long id);
	Super findByName(String name);
}
