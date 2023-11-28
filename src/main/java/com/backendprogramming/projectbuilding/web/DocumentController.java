package com.backendprogramming.projectbuilding.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@Controller
public class DocumentController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private DocumentRepository drepository;

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

	// new apartment document form page
	@RequestMapping(value = "/adddocumentapartment/{apartmentId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public String addDocumentApartment(@PathVariable Long apartmentId, Model model) {
		Apartment apartment = arepository.findById(apartmentId).get();
		Document document = new Document();
		document.setApartment(apartment);
		model.addAttribute("document", document);
		model.addAttribute("apartment", apartment);
		return "adddocumentapartment";
	}

}
