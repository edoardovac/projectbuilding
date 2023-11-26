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
import org.springframework.web.bind.annotation.RequestParam;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;

@Controller
public class ApartmentController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private AppUserRepository urepository;
	

	// show apartments from a specific building
	@RequestMapping(value = "/apartments/{buildingId}")
	public String buildingApartments(@PathVariable("buildingId") Long buildingId, Model model) {
		// get current user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// if role is USER then shows only buildings belonging to him/her
		if (authentication.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("USER"))) {
			// if role is USER then shows only buildings belonging to him/her
			AppUser appUser = urepository.findByUsername(authentication.getName());
			Apartment apartment = appUser.getApartment();
			// create a list so that the template works without additional code
			List<Apartment> apartments = new ArrayList<>();
			apartments.add(apartment);
			model.addAttribute("apartments", apartments);
			model.addAttribute("building", appUser.getBuilding());
			return "apartmentlist";
		} else {
			Building building = brepository.findById(buildingId).get();
			List<Apartment> apartments = building.getApartments();
			model.addAttribute("apartments", apartments);
			model.addAttribute("building", building);
			return "apartmentlist";
		}
	}
	
	// edit an apartment
	@RequestMapping(value = "/editapartment/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editApartment(@PathVariable("id") Long apartmentId, Model model) {
		// .get() needed to throw an exception
		Apartment apartment = arepository.findById(apartmentId).get();
		model.addAttribute("apartment", apartment);
		return "editapartment";
	}

	// save new apartment and redirect to building's apartment list
	@RequestMapping(value = "/saveapartment", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveApartment(Apartment apartment, @RequestParam Long buildingId) {
		Building building = brepository.findById(buildingId).get();
		apartment.setBuilding(building);
		arepository.save(apartment);
		return "redirect:/apartments/" + buildingId;
	}

	// delete an apartment
	@RequestMapping(value = "/deleteapartment/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SUPER')")
	public String deleteApartment(@PathVariable("id") Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		arepository.deleteById(apartmentId);
		return "redirect:../apartments/" + apartment.getBuilding().getId();
	}
	

	// new apartment form page
	@RequestMapping(value = "/addapartment/{buildingId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addApartment(@PathVariable Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();

		model.addAttribute("apartment", new Apartment());
		model.addAttribute("building", building);
		return "addapartment";
	}

}
