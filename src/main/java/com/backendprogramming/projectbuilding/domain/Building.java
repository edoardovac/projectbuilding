package com.backendprogramming.projectbuilding.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "building")
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private int numberOfStairs;
	@Column(nullable = false)
	private int numberOfApartments;

	@JsonIgnore
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
	private List<Apartment> apartments;

	@JsonIgnore
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
	private List<Document> documents;
	
	@JsonIgnore
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
