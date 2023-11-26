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
@Table(name = "document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String fileName;
	@Column(nullable = false)
	private String type;
	private String description;
	@Column(nullable = false)
	private String documentDate;
	@Column(nullable = false)
	private String documentNumber;
		
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
    
    public Document() {
    }

	public Document(String fileName, String type, String description, String documentDate,
			String documentNumber, Building building) {
		super();
		this.fileName = fileName;
		this.type = type;
		this.description = description;
		this.documentDate = documentDate;
		this.documentNumber = documentNumber;
		this.building = building;
	}

	public Document(String fileName, String type, String description, String documentDate,
			String documentNumber, Apartment apartment) {
		super();
		this.fileName = fileName;
		this.type = type;
		this.description = description;
		this.documentDate = documentDate;
		this.documentNumber = documentNumber;
		this.apartment = apartment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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
