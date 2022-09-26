package com.superhero.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.model.Location;
import com.superhero.model.Sighting;
import com.superhero.repository.LocationRepository;
import com.superhero.repository.SightingRepository;

@Service
public class SightingsService {
	@Autowired
	SightingRepository sightingRepository;
	@Autowired
	LocationRepository locationRepositry;
	
	public void newSighting(String name, LocalDate date, String locationName) {
		Location location = locationRepositry.findByName(locationName);
		Sighting tempSighting = new Sighting();
		tempSighting.setName(name);
		tempSighting.setDate(date);
		tempSighting.setLocation(location);
		sightingRepository.save(tempSighting);
	}
	
	public List<Sighting> getSightings(){
		return (List<Sighting>) sightingRepository.findAll();
	}
	public Sighting getSighting(Long id) {
		return sightingRepository.findById(id).orElseThrow(() -> new IllegalStateException("SightingId does not exist"));
	}
	public void editSighting(String name, LocalDate date, String locationName) {
		Location location = locationRepositry.findByName(locationName);
		Sighting tempSighting = sightingRepository.findByName(name);
		tempSighting.setName(name);
		tempSighting.setDate(date);
		tempSighting.setLocation(location);
		sightingRepository.save(tempSighting);
	}
}
