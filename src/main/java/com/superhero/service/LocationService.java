package com.superhero.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.model.Location;
import com.superhero.repository.LocationRepository;
import com.superhero.repository.SightingRepository;

@Service
public class LocationService {
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	SightingRepository sightingRepository;
	
	public void newLocation(String name, double longitude, double latitude) {
		Location tempLocation = new Location();
		tempLocation.setName(name);
		tempLocation.setLatitude(latitude);;
		tempLocation.setLongitude(longitude);
		locationRepository.save(tempLocation);
	}
	
	public List<Location> getLocations(){
		return (List<Location>) locationRepository.findAll();
	}
	public Location getLocation(Long id) {
		return locationRepository.findById(id).orElseThrow(() -> new IllegalStateException("LocationId does not exist"));
	}
	public void editLocation(String name, double longitude, double latitude) {
		Location tempLocation = locationRepository.findByName(name);
		tempLocation.setName(name);
		tempLocation.setLatitude(latitude);
		tempLocation.setLongitude(longitude);
		locationRepository.save(tempLocation);
	}
}
