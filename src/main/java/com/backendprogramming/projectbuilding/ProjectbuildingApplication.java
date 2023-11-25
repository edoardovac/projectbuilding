package com.backendprogramming.projectbuilding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;

@SpringBootApplication
public class ProjectbuildingApplication {

	private static final Logger log = LoggerFactory.getLogger(ProjectbuildingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectbuildingApplication.class, args);
	}

	@Bean
	public CommandLineRunner projectBuildingDemo(BuildingRepository brepository, ApartmentRepository arepository,
			DocumentRepository drepository) {
		return (args) -> {
			log.info("save a couple of buildings");
			Building buildingOne = new Building("Made-up Street 123", 5, 10);
			brepository.save(buildingOne);

			Building buildingTwo = new Building("Ratapihantie 13", 1, 25);
			brepository.save(buildingTwo);

			log.info("save a couple of apartments");
			Apartment apartmentOne = new Apartment("Mario", "Rossi", "101", buildingOne);
			arepository.save(apartmentOne);

			Apartment apartmentTwo = new Apartment("John", "Doe", "3A", buildingTwo);
			arepository.save(apartmentTwo);

			log.info("save a couple of building documents");
			Document documentOne = new Document("BuildingDoc1.pdf", "PDF", "Building document description 1",
					"2023-01-01", "2023-01-05", "BD001", buildingOne);
			drepository.save(documentOne);

			Document documentTwo = new Document("BuildingDoc2.pdf", "PDF", "Building document description 2",
					"2023-02-01", "2023-02-10", "BD002", buildingOne);
			drepository.save(documentTwo);

			Document documentThree = new Document("BuildingDoc3.pdf", "PDF", "Building document description 3",
					"2023-03-01", "2023-03-15", "BD003", buildingTwo);
			drepository.save(documentThree);

			Document documentFour = new Document("BuildingDoc4.pdf", "PDF", "Building document description 4",
					"2023-04-01", "2023-04-20", "BD004", buildingTwo);
			drepository.save(documentFour);

			log.info("save a couple of apartment documents");
			Document documentFive = new Document("ApartmentDoc1.pdf", "PDF", "Apartment document description 1",
					"2023-05-01", "2023-05-05", "AD001", apartmentOne);
			drepository.save(documentFive);

			Document documentSix = new Document("ApartmentDoc2.pdf", "PDF", "Apartment document description 2",
					"2023-06-01", "2023-06-10", "AD002", apartmentOne);
			drepository.save(documentSix);

			Document documentSeven = new Document("ApartmentDoc3.pdf", "PDF", "Apartment document description 3",
					"2023-07-01", "2023-07-15", "AD003", apartmentTwo);
			drepository.save(documentSeven);

			Document documentEight = new Document("ApartmentDoc4.pdf", "PDF", "Apartment document description 4",
					"2023-08-01", "2023-08-20", "AD004", apartmentTwo);
			drepository.save(documentEight);
		};

	}

}
