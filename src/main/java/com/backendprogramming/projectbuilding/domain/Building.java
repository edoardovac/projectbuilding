package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String address;
	private int numberOfStairs;
	private int numberOfApartments;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
	private List<Apartment> apartments;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
	private List<Document> documents;
	
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
	private List<AppUser> appUsers;
	
	public Building() {
	}

	public Building(String address, int numberOfStairs, int numberOfApartments) {
		super();
		this.address = address;
		this.numberOfStairs = numberOfStairs;
		this.numberOfApartments = numberOfApartments;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumberOfStairs() {
		return numberOfStairs;
	}

	public void setNumberOfStairs(int numberOfStairs) {
		this.numberOfStairs = numberOfStairs;
	}

	public int getNumberOfApartments() {
		return numberOfApartments;
	}

	public void setNumberOfApartments(int numberOfApartments) {
		this.numberOfApartments = numberOfApartments;
	}

	public List<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
