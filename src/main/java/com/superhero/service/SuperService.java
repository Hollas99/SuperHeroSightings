package com.superhero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.model.Organisation;
import com.superhero.model.Super;
import com.superhero.repository.OrganisationRepository;
import com.superhero.repository.SuperRepository;

@Service
public class SuperService {
	
	@Autowired
	SuperRepository superRepository;
	@Autowired
	OrganisationRepository organisationRepository;
	
	public void newSuper(String name, String description, String superpower, Optional<String> orgName) {
		@SuppressWarnings("unused")
		Organisation org;
		if (!orgName.isEmpty())
			org = organisationRepository.findByName(orgName.orElseThrow(() -> new IllegalStateException("Org name does not exist!")));
		Super tempSuper = new Super();
		tempSuper.setName(name);
		tempSuper.setDescription(description);
		tempSuper.setSuperpower(superpower);
		superRepository.save(tempSuper);
	}
	
	public List<Super> getSupers(){
		return (List<Super>) superRepository.findAll();
	}
	public Super getSuper(Long id) {
		return superRepository.findById(id).orElseThrow(() -> new IllegalStateException("SuperId does not exist"));
	}
	public void editSuper(String name, String description, String superpower, Optional<String> orgName) {
		Super tempSuper = superRepository.findByName(name);
		tempSuper.setName(name);
		tempSuper.setDescription(description);
		tempSuper.setSuperpower(superpower);
		if (!orgName.isEmpty()) {
			Organisation org = organisationRepository.findByName(orgName.orElseThrow(()-> new IllegalStateException("Organisation name does not exist")));
			tempSuper.setOrg(org);
		}
		superRepository.save(tempSuper);
	}
	
}