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

import com.superhero.model.Organisation;
import com.superhero.service.OrganisationService;

@Controller
public class OrgController {
	@Autowired
	private OrganisationService orgService;
	
	

	@RequestMapping("/addOrg")
	public String addOrg() {
		return "addOrg";
	}
	@RequestMapping("/listOrgs")
	public String listOrgs(Model model) {
		List<Organisation> orgs = orgService.getOrgs();
		model.addAttribute("orgsList", orgs);
		return "listOrgs";
	}
	
	@RequestMapping("/editOrg")
	public String editOrg(Model model) {		
		List<Organisation> orgs = orgService.getOrgs();
		model.addAttribute("orgsList", orgs);
		Organisation orgObj = orgService.getOrg((long) 1);
		model.addAttribute("org", orgObj);
		return "editOrg";
	}
	
	@PostMapping("/editOrg") 
	public String editOrgById(@RequestParam("orgOptions") Long orgId, Model model) {	
		List<Organisation> orgs = orgService.getOrgs();
		model.addAttribute("orgsList", orgs);
		Organisation orgObj = orgService.getOrg(orgId);
		model.addAttribute("org", orgObj);
		return "editOrg";
	}
	@PostMapping("/postOrg") //Submit the edit
	public String editOrgChanges(
			@RequestParam("name") String name, 
			@RequestParam("description") String desc,
			@RequestParam("contactInfo") String contactInfo,
			@RequestParam("supers") Optional<String> supers) {
		orgService.editOrg(name, desc, contactInfo, supers);
		return "index";
	}
	
	@PostMapping("org/add")
	@ResponseStatus(code=HttpStatus.CREATED)
	public String createOrg(
			@RequestParam(required=true) String name,
			@RequestParam(required=true) String description,
			@RequestParam(required=true) String contactInfo,
			@RequestParam(required=false) Optional<String> supers) {
		orgService.newOrg(name, description, contactInfo, supers);
		return "addOrg";
	}
	@GetMapping("org/list")
	public List<Organisation> listOrgs(){
		return orgService.getOrgs();
	}
	
}
