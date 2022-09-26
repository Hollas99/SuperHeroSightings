package com.superhero.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.superhero.model.Location;
import com.superhero.service.LocationService;


@Controller
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/addLocation")
	public String addLocation() {
		return "addLocation";
	}
	@RequestMapping("/listLocations")
	public String listLocation(Model model) {
		List<Location> locations = locationService.getLocations();
		model.addAttribute("locationsList", locations);
		return "listLocations";
	}
	
	@RequestMapping("/editLocation")
	public String editLocation(Model model) {		
		List<Location> locations = locationService.getLocations();
		model.addAttribute("locationsList", locations);
		Location locationObj = locationService.getLocation((long) 1);
		model.addAttribute("location", locationObj);
		return "editLocation";
	}
	
	@PostMapping("/editLocation") //Selecting the location
	public String editLocationById(@RequestParam("locationOptions") Long locationId, Model model) {	
		List<Location> locations = locationService.getLocations();
		model.addAttribute("locationsList", locations);
		Location locationObj = locationService.getLocation(locationId);
		model.addAttribute("location", locationObj);
		return "editLocation";
	}
	@PostMapping("/postLocation") //Submit the edit
	public String editLocationChanges(
			@RequestParam("name") String name, 
			@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude) {
		locationService.editLocation(name, longitude, latitude);
		return "index";
	}
	
	
	
	@PostMapping(path="location/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public String createLocation(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) double longitude,
			@RequestParam(required=true) double latitude) {
		locationService.newLocation(name, longitude, latitude);
		return "addLocation";
	}
	@GetMapping(path="location/list")
	public List<Location> listLocations(){
		return locationService.getLocations();
	}
}
