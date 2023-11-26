package com.backendprogramming.projectbuilding.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	// Login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// show all buildings
	@RequestMapping(value = "/buildings")
	public String buildingList(Model model) {
		model.addAttribute("buildings", brepository.findAll());
		return "buildinglist";
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

	// show apartments from a specific building
	@RequestMapping(value = "/apartments/{buildingId}")
	public String buildingApartments(@PathVariable("buildingId") Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();
		List<Apartment> apartments = building.getApartments();
		model.addAttribute("apartments", apartments);
		model.addAttribute("building", building);
		return "apartmentlist";
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

	// show specific building documents
	@RequestMapping(value = "/buildingdocuments/{buildingId}")
	public String buildingDocumentsList(@PathVariable("buildingId") Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();
		List<Document> documents = building.getDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("buildingId", buildingId);
		model.addAttribute("building", building);
		return "buildingdocuments";
	}

	// show specific apartment documents
	@RequestMapping(value = "/apartmentdocuments/{apartmentId}")
	public String apartmentDocumentsList(@PathVariable("apartmentId") Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		Building building = apartment.getBuilding();
		List<Document> documents = apartment.getDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("apartment", apartment);
		model.addAttribute("building", building);
		return "apartmentdocuments";
	}

	// delete a document
	@RequestMapping(value = "deletedocument/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('SUPER')")
	public String deleteDocument(@PathVariable("id") Long documnentId, Model model) {
		Document document = drepository.findById(documnentId).get();
		drepository.deleteById(documnentId);
		// redirects to correct page
		if (document.getBuilding() != null) {
			return "redirect:../buildingdocuments/" + document.getBuilding().getId();
		} else if (document.getApartment() != null) {
			return "redirect:../apartmentdocuments/" + document.getApartment().getId();
		} else {
			return "buildinglist";
		}
	}

	// edit a document
	@RequestMapping(value = "/editdocument/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editDocument(@PathVariable("id") Long documentId, Model model) {
		// .get() needed to throw an exception
		Document document = drepository.findById(documentId).get();
		model.addAttribute("document", document);
		if (document.getBuilding() != null) {
			Long buildingId = document.getBuilding().getId();
			model.addAttribute("buildingId", buildingId);
			return "editdocument";
		} else if (document.getApartment() != null) {
			Long apartmentId = document.getApartment().getId();
			model.addAttribute("apartmentId", apartmentId);
			return "editdocument";
		} else {
			return "buildinglist";
		}

	}

	// save new building document
	@RequestMapping(value = "/savedocument", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveDocument(Document document) {
		if (document.getBuilding() != null) {
			Long buildingId = document.getBuilding().getId();
			Building building = brepository.findById(buildingId).get();
			document.setBuilding(building);
			drepository.save(document);
			return "redirect:/buildingdocuments/" + buildingId;
		} else if (document.getApartment() != null) {
			Long apartmentId = document.getApartment().getId();
			Apartment apartment = arepository.findById(apartmentId).get();
			document.setApartment(apartment);
			drepository.save(document);
			return "redirect:/apartmentdocuments/" + apartmentId;
		} else {
			return "buildinglist";
		}

	}

	// new building document form page
	@RequestMapping(value = "/adddocumentbuilding/{buildingId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addDocumentBuilding(@PathVariable Long buildingId, Model model) {
		Building building = brepository.findById(buildingId).get();
		Document document = new Document();
		document.setBuilding(building);
		model.addAttribute("document", document);
		model.addAttribute("building", building);
		return "adddocumentbuilding";
	}

	// new building document form page
	@RequestMapping(value = "/adddocumentapartment/{apartmentId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addDocumentApartment(@PathVariable Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		Document document = new Document();
		document.setApartment(apartment);
		model.addAttribute("document", document);
		model.addAttribute("apartment", apartment);
		return "adddocumentapartment";
	}

	// for REST either use apartment/{id} and apartments/{buildingId} to
	// differentiate them
	// the first gives the apartment info, while the second a list of apartments
	// that belong to a single building

}
