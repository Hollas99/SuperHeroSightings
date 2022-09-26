package com.superhero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.superhero.model.Organisation;
import com.superhero.model.Super;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long>{
	//List<Super> findByOrganisation(Super supers);
	Organisation findByName(String name);
}