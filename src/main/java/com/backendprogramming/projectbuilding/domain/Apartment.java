package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Apartment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String ownerName;
	private String ownerSurname;
	private String aptNumber;

	@ManyToOne
	@JoinColumn(name = "building_id")
	private Building building;

	@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
	private List<Document> documents;

	public Apartment() {
	}

	public Apartment(String ownerName, String ownerSurname, String aptNumber, Building building) {
		this.ownerName = ownerName;
		this.ownerSurname = ownerSurname;
		this.aptNumber = aptNumber;
		this.building = building;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSurname() {
		return ownerSurname;
	}

	public void setOwnerSurname(String ownerSurname) {
		this.ownerSurname = ownerSurname;
	}

	public String getAptNumber() {
		return aptNumber;
	}

	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
