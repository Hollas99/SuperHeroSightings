package com.superhero.controller;

import java.time.LocalDate;
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

import com.superhero.model.Sighting;
import com.superhero.service.SightingsService;

@Controller
public class SightingController {
	@Autowired
	private SightingsService sightingService;
	
	@RequestMapping("/addSighting")
	public String addSighting() {
		return "addSighting";
	}
	@RequestMapping("/listSightings")
	public String listSighting(Model model) {
		List<Sighting> sightings = sightingService.getSightings();
		model.addAttribute("sightingsList", sightings);
		return "listSightings";
	}
	
	@RequestMapping("/editSighting")
	public String editSighting(Model model) {		
		List<Sighting> sightings = sightingService.getSightings();
		model.addAttribute("sightingsList", sightings);
		Sighting sightingObj = sightingService.getSighting((long) 1);
		model.addAttribute("sighting", sightingObj);
		return "editSighting";
	}
	
	@PostMapping("/editSighting") //Selecting the sighting
	public String editSightingById(@RequestParam("sightingOptions") Long sightingId, Model model) {	
		List<Sighting> sightings = sightingService.getSightings();
		model.addAttribute("sightingsList", sightings);
		Sighting sightingObj = sightingService.getSighting(sightingId);
		model.addAttribute("sighting", sightingObj);
		return "editSighting";
	}
	@PostMapping("/postSighting") //Submit the edit
	public String editSightingChanges(
			@RequestParam("name") String name, 
			@RequestParam("date") LocalDate date,
			@RequestParam("location") String location) {
		sightingService.editSighting(name, date, location);
		return "index";
	}
	
	
	
	@PostMapping("sighting/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public String createSighting(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) LocalDate date,
			@RequestParam(required=true) String location) {
		sightingService.newSighting(name, date, location);
		return "addSighting";
	}
	@GetMapping("sighting/list")
	public List<Sighting> listSightings(){
		return sightingService.getSightings();
	}
}
