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
			/*
			log.info("save buildings");
			Building buildingOne = new Building("Ratapihantie 13", 1, 6);
			Building buildingTwo = new Building("1600 Pennsylvania Avenue", 2, 10);
			Building buildingThree = new Building("Mannerheimintie 30", 3, 9);
			brepository.save(buildingOne);
			brepository.save(buildingTwo);
			brepository.save(buildingThree);

			log.info("save apartments and their documents and users for buildingOne");
			String[] namesOne = { "Emily", "Alexander", "Olivia", "Liam", "Sophia" };
			String[] surnamesOne = { "Smith", "Johnson", "Brown", "Davis", "Miller" };
			for (int i = 0; i < namesOne.length; i++) {
				Apartment apartment = new Apartment(namesOne[i], surnamesOne[i], i + 1, buildingOne);
				arepository.save(apartment);
				
				Document document = new Document("ApartmentDoc" + (i * 17), "PDF", "Apartment document description " + (i * 17),
						"2023-05-01", "AD" + (i * 13), apartment);
				drepository.save(document);

				AppUser appUser = new AppUser(namesOne[i] + surnamesOne[i],
						"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", buildingOne, apartment);
				urepository.save(appUser);
			}

			log.info("save apartments and their documents and users for buildingTwo");
			String[] namesTwo = { "Aino", "Eero", "Sofia", "Aleksi", "Emma", "Sophia", "Liam", "Olivia", "Alexander",
					"Emily" };
			String[] surnamesTwo = { "Virtanen", "Korhonen", "Mäkinen", "Nieminen", "Koskinen", "Smith", "Johnson",
					"Brown", "Davis", "Miller" };
			for (int j = 0; j < namesTwo.length; j++) {
				Apartment apartment = new Apartment(namesTwo[j], surnamesTwo[j], j + 1, buildingTwo);
				arepository.save(apartment);
				
				Document document = new Document("ApartmentDoc" + (j * 14), "PDF", "Apartment document description " + (j * 14),
						"2023-05-01", "AD" + (j * 27), apartment);
				drepository.save(document);

				AppUser appUser = new AppUser(surnamesTwo[j] + namesTwo[j],
						"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", buildingTwo, apartment);
				urepository.save(appUser);
			}
			
			log.info("save apartments and their documents and users for buildingThree");
			String[] namesThree = { "Emma", "Aleksi", "Sofia", "Eero", "Aino", "Sophia", "Liam", "Olivia",
					"Alexander" };
			String[] surnamesThree = { "Smith", "Johnson", "Brown", "Davis", "Miller", "Virtanen", "Korhonen",
					"Mäkinen", "Nieminen" };
			for (int k = 0; k < namesThree.length; k++) {
				Apartment apartment = new Apartment(namesThree[k], surnamesThree[k], k + 1, buildingThree);
				arepository.save(apartment);
				
				Document document = new Document("ApartmentDoc" + (k * 43), "PDF", "Apartment document description " + (k * 43),
						"2023-05-01", "AD" + (k * 18), apartment);
				drepository.save(document);

				AppUser appUser = new AppUser(surnamesThree[k] + namesThree[k],
						"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", buildingThree,
						apartment);
				urepository.save(appUser);
			}

			log.info("save building documents");		
			Document documentOne = new Document("BuildingDoc1.pdf", "PDF", "Building document description 1", "2023-01-01",
					"BD001", buildingOne);
			drepository.save(documentOne);
			Document documentTwo = new Document("BuildingDoc2.pdf", "PDF", "Building document description 2", "2023-02-01",
					"BD002", buildingOne);
			drepository.save(documentTwo);
			Document documentThree = new Document("BuildingDoc3.pdf", "PDF", "Building document description 3",
					"2023-03-01", "BD003", buildingTwo);
			drepository.save(documentThree);
			Document documentFour = new Document("BuildingDoc4.pdf", "PDF", "Building document description 4", "2023-04-01",
					"BD004", buildingTwo);
			drepository.save(documentFour);			
			Document documentFive = new Document("BuildingDoc3.pdf", "PDF", "Building document description 3",
					"2023-03-01", "BD003", buildingThree);
			drepository.save(documentFive);
			Document documentSix = new Document("BuildingDoc4.pdf", "PDF", "Building document description 4", "2023-04-01",
					"BD004", buildingThree);
			drepository.save(documentSix);
			
			log.info("save admin and super users");
			AppUser userOne = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
			AppUser userTwo = new AppUser("super", "$2a$12$6YigCulpKaYliLQbgcFSMe69/eMLK/S9faod.gS4/6ZBAuvi3bZFW",
					"SUPER");
			urepository.save(userOne);
			urepository.save(userTwo);
			*/
		};

		

	}

}
