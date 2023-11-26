package com.backendprogramming.projectbuilding.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	// User name with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password_hash", nullable = false)
	private String password_hash;

	@Column(name = "role", nullable = false)
	private String role;
	
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

	public AppUser() {
	}

	public AppUser(String username, String password_hash, String role) {
		super();
		this.username = username;
		this.password_hash = password_hash;
		this.role = role;
	}
	
	public AppUser(String username, String password_hash, String role, Building building,
			Apartment apartment) {
		super();
		this.username = username;
		this.password_hash = password_hash;
		this.role = role;
		this.building = building;
		this.apartment = apartment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return this.password_hash;
	}

	public void setPasswordHash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
	
}
