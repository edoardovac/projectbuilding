package com.backendprogramming.projectbuilding.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@RestController
public class ProjectBuildingRestController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private DocumentRepository drepository;

	// RESTful service to get all buildings
	@RequestMapping(value = "/building", method = RequestMethod.GET)
	public @ResponseBody List<Building> buildingListRest() {
		return (List<Building>) brepository.findAll();
	}

	// RESTful service to get building by id
	@RequestMapping(value = "/building/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Building> findBuildingRest(@PathVariable("id") Long buildingId) {
		return brepository.findById(buildingId);
	}

	// RESTful service to get all apartment
	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	public @ResponseBody List<Apartment> apartmentListRest() {
		return (List<Apartment>) arepository.findAll();
	}

	// RESTful service to get apartment by id
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Apartment> findApartmentRest(@PathVariable("id") Long apartmentId) {
		return arepository.findById(apartmentId);
	}

	// RESTful service to get all documents
	@RequestMapping(value = "/document", method = RequestMethod.GET)
	public @ResponseBody List<Document> documentListRest() {
		return (List<Document>) drepository.findAll();
	}

	// RESTful service to get document by id
	@RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Document> findDocumentRest(@PathVariable("id") Long documentId) {
		return drepository.findById(documentId);
	}

}
