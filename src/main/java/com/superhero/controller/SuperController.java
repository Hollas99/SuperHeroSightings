package com.superhero.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.superhero.model.Super;
import com.superhero.service.SuperService;

@Controller
public class SuperController {
	@Autowired
	private SuperService superService;
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/addSuper")
	public String addSuper() {
		return "addSuper";
	}
	@RequestMapping("/listSupers")
	public String listSuper(Model model) {
		List<Super> supers = superService.getSupers();
		model.addAttribute("supersList", supers);
		return "listSupers";
	}
	
	@RequestMapping("/editSuper")
	public String editSuper(Model model) {		
		List<Super> supers = superService.getSupers();
		model.addAttribute("supersList", supers);
		Super superObj = superService.getSuper((long) 1);
		model.addAttribute("super", superObj);
		return "editSuper";
	}
	
	@PostMapping("/editSuper") //Selecting the super
	public String editSuperById(@RequestParam("superOptions") Long superId, Model model) {	
		List<Super> supers = superService.getSupers();
		model.addAttribute("supersList", supers);
		Super superObj = superService.getSuper(superId);
		model.addAttribute("super", superObj);
		return "editSuper";
	}
	@PostMapping("/postSuper") //Submit the edit
	public String editSuperChanges(
			@RequestParam("name") String name, 
			@RequestParam("description") String desc,
			@RequestParam("superpower") String power,
			@RequestParam("org") Optional<String> org) {
		superService.editSuper(name, desc, power, org);
		return "index";
	}
	
	
	
	@PostMapping(path="super/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public String createSuper(
			@RequestParam(required=true) String superName,
			@RequestParam(required=true) String superDesc,
			@RequestParam(required=true) String superPower,
			@RequestParam(required=false) Optional<String> organisation) {
		superService.newSuper(superName, superDesc, superPower, organisation);
		return "addSuper";
	}
	@GetMapping(path="super/list")
	public List<Super> listSupers(){
		return superService.getSupers();
	}
	
}

