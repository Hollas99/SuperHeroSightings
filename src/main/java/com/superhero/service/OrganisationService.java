package com.superhero.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.model.Organisation;
import com.superhero.model.Super;
import com.superhero.repository.OrganisationRepository;
import com.superhero.repository.SuperRepository;

@Service
public class OrganisationService {


	@Autowired
	SuperRepository superRepository;
	@Autowired
	OrganisationRepository orgRepository;
	
	public void newOrg(String name, String description, String contactInfo, Optional<String> supers) {
		Organisation org = new Organisation();
		org.setName(name);
		org.setDescription(description);
		org.setContactInfo(contactInfo);
		if (!supers.isEmpty()) {
			String[] values = supers.get().split(",");
			Set<Super> supersSet = new HashSet<Super>();
			for (String s : values) {
				supersSet.add(superRepository.findByName(s));
			}
			org.setSupers(supersSet);
		}
		orgRepository.save(org);
	}

	public List<Organisation> getOrgs(){
		return (List<Organisation>) orgRepository.findAll();
	}
	
	public Organisation getOrg(Long id) { 
		return orgRepository.findById(id).orElseThrow(() -> new IllegalStateException("OrgId does not exist"));
	}
	public void editOrg(String name, String description, String contactInfo, Optional<String> supers) {
		Organisation org = orgRepository.findByName(name);
		org.setName(name);
		org.setDescription(description);
		org.setContactInfo(contactInfo);
		if (!supers.isEmpty()) {
			String[] values = supers.get().split(",");
			Set<Super> supersSet = new HashSet<Super>();
			for (String s : values) {
				supersSet.add(superRepository.findByName(s));
			}
			org.setSupers(supersSet);
		}
		orgRepository.save(org);
	}
}