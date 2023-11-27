package com.backendprogramming.projectbuilding.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;

@Controller
public class BuildingController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private AppUserRepository urepository;

	// show all buildings
	@RequestMapping(value = "/buildings")
	public String buildingList(Model model) {
		// get current user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// if role is USER then shows only buildings belonging to him/her
		if (authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("USER"))) {
			// find building belonging to current active user
			AppUser appUser = urepository.findByUsername(authentication.getName());
			if (appUser.getBuilding() != null) {
			Building building = appUser.getBuilding();
			// create a list so that the template works without additional code
			List<Building> buildings = new ArrayList<>();
			buildings.add(building);
			model.addAttribute("buildings", buildings);
			return "buildinglist";
			} else {
				return "contactadmin";
			}
		} else {
			model.addAttribute("buildings", brepository.findAll());
			return "buildinglist";
		}
	}

	// new building form page
	@RequestMapping(value = "/addbuilding")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addBuilding(Model model) {
		model.addAttribute("building", new Building());
		return "addbuilding";
	}

	// edit a building
	@RequestMapping(value = "/editbuilding/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBuilding(@PathVariable("id") Long buildingId, Model model) {
		// .get() needed to throw an exception
		Building building = brepository.findById(buildingId).get();
		model.addAttribute("building", building);
		return "editbuilding";
	}

	// save building and redirect to building list
	@RequestMapping(value = "/savebuilding", method = { RequestMethod.POST, RequestMethod.GET })
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveBuilding(Building building) {
		brepository.save(building);
		return "redirect:/buildings";
	}

	// delete a building
	@RequestMapping(value = "/deletebuilding/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SUPER')")
	public String deleteBuilding(@PathVariable("id") Long buildingId, Model model) {
		brepository.deleteById(buildingId);
		return "redirect:../buildings";
	}

}
