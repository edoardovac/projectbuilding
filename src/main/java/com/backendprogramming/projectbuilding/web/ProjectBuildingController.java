package com.backendprogramming.projectbuilding.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@Controller
public class ProjectBuildingController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private DocumentRepository drepository;

	// show all buildings
	@RequestMapping(value = "/buildings")
	public String buildingList(Model model) {
		model.addAttribute("buildings", brepository.findAll());
		return "buildinglist";
	}

	// new building form page
	@RequestMapping(value = "/addbuilding")
	public String addBuilding(Model model) {
		model.addAttribute("building", new Building());
		return "addbuilding";
	}

	// edit a building
	@RequestMapping(value = "/editbuilding/{id}", method = RequestMethod.GET)
	public String editBuilding(@PathVariable("id") Long buildingId, Model model) {
		// .get() needed to throw an exception
		Building building = brepository.findById(buildingId).get();
		model.addAttribute("building", building);
		return "editbuilding";
	}

	// save building and redirect to building list
	@RequestMapping(value = "/savebuilding", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveBuilding(Building building) {
		brepository.save(building);
		return "redirect:/buildings";
	}

	// delete a building
	@RequestMapping(value = "/deletebuilding/{id}", method = RequestMethod.GET)
	public String deleteBuilding(@PathVariable("id") Long buildingId, Model model) {
		brepository.deleteById(buildingId);
		return "redirect:../buildings";
	}

	// new apartment form page
	@RequestMapping(value = "/addapartment/{buildingId}")
	public String addApartment(@PathVariable Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();

		model.addAttribute("apartment", new Apartment());
		model.addAttribute("building", building);
		return "addapartment";
	}

	// edit an apartment
	@RequestMapping(value = "/editapartment/{id}", method = RequestMethod.GET)
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
	public String deleteApartment(@PathVariable("id") Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		arepository.deleteById(apartmentId);
		return "redirect:../apartments/" + apartment.getBuilding().getId();
	}

	// show apartments from a specific building
	@RequestMapping(value = "/apartments/{buildingId}")
	public String buildingApartments(@PathVariable("buildingId") Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();
		List<Apartment> apartments = building.getApartments();
		model.addAttribute("apartments", apartments);
		model.addAttribute("buildingId", buildingId);

		return "apartmentlist";
	}

	// show specific building documents
	@RequestMapping(value = "/buildingdocuments/{buildingId}")
	public String buildingDocumentsList(@PathVariable("buildingId") Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();
		List<Document> documents = building.getDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("buildingId", buildingId);
		return "buildingdocuments";
	}
	
	// show specific apartment documents
	@RequestMapping(value = "/apartmentdocuments/{apartmentId}")
	public String apartmentDocumentsList(@PathVariable("apartmentId") Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		Building building = apartment.getBuilding();
		List<Document> documents = apartment.getDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("apartmentId", apartmentId);
		model.addAttribute("building", building);
		return "apartmentdocuments";
	}
	
	// for REST either use apartment/{id} and apartments/{buildingId} to
	// differentiate them
	// the first gives the apartment info, while the second a list of apartments
	// that belong to a single building

}
