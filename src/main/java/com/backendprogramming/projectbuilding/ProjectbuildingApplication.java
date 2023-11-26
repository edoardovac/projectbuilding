package com.backendprogramming.projectbuilding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;
import com.backendprogramming.projectbuilding.domain.Document;
import com.backendprogramming.projectbuilding.domain.DocumentRepository;
import com.backendprogramming.projectbuilding.domain.AppUser;

@SpringBootApplication
public class ProjectbuildingApplication {

	private static final Logger log = LoggerFactory.getLogger(ProjectbuildingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectbuildingApplication.class, args);
	}

	@Bean
	public CommandLineRunner projectBuildingDemo(BuildingRepository brepository, ApartmentRepository arepository,
			DocumentRepository drepository, AppUserRepository urepository) {
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
					"2023-01-01", "BD001", buildingOne);
			drepository.save(documentOne);

			Document documentTwo = new Document("BuildingDoc2.pdf", "PDF", "Building document description 2",
					"2023-02-01", "BD002", buildingOne);
			drepository.save(documentTwo);

			Document documentThree = new Document("BuildingDoc3.pdf", "PDF", "Building document description 3",
					"2023-03-01", "BD003", buildingTwo);
			drepository.save(documentThree);

			Document documentFour = new Document("BuildingDoc4.pdf", "PDF", "Building document description 4",
					"2023-04-01", "BD004", buildingTwo);
			drepository.save(documentFour);

			log.info("save a couple of apartment documents");
			Document documentFive = new Document("ApartmentDoc1.pdf", "PDF", "Apartment document description 1",
					"2023-05-01", "AD001", apartmentOne);
			drepository.save(documentFive);

			Document documentSix = new Document("ApartmentDoc2.pdf", "PDF", "Apartment document description 2",
					"2023-06-01", "AD002", apartmentOne);
			drepository.save(documentSix);

			Document documentSeven = new Document("ApartmentDoc3.pdf", "PDF", "Apartment document description 3",
					"2023-07-01", "AD003", apartmentTwo);
			drepository.save(documentSeven);

			Document documentEight = new Document("ApartmentDoc4.pdf", "PDF", "Apartment document description 4",
					"2023-08-01", "AD004", apartmentTwo);
			drepository.save(documentEight);
			
			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user",
			"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin",
			"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			AppUser user3 = new AppUser("super", "$2a$12$6YigCulpKaYliLQbgcFSMe69/eMLK/S9faod.gS4/6ZBAuvi3bZFW", "SUPER");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
		};

	}

}
