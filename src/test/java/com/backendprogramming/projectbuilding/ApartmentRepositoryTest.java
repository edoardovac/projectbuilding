package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendprogramming.projectbuilding.domain.Apartment;
import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;

@SpringBootTest
public class ApartmentRepositoryTest {
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private BuildingRepository brepository;

	private Building building = new Building("11 Downing Street", 2, 6);
	Apartment apartment = new Apartment("Donald", "Duck", 999, building);
	
	@Test
	public void createNewApartmentTest() {
		brepository.save(building);
		arepository.save(apartment);
		assertThat(apartment.getId()).isNotNull();
		assertThat(arepository.findById(apartment.getId())).isNotNull();
		arepository.delete(apartment);
		brepository.delete(building);
	}

	@Test
	public void findByOwnerSurnameShouldReturnApartment() {
		brepository.save(building);
		arepository.save(apartment);
		List<Apartment> apartments = arepository.findByOwnerSurname("Duck");
		assertThat(apartments).hasSize(1);
		assertThat(apartments.get(0).getOwnerSurname()).isEqualTo("Duck");
		arepository.delete(apartment);
		brepository.delete(building);
	}
	
	@Test
	public void findByAptNumberShouldReturnApartment() {
		brepository.save(building);
		arepository.save(apartment);
		List<Apartment> apartments = arepository.findByAptNumber(999);
		assertThat(apartments).hasSize(1);
		assertThat(apartments.get(0).getAptNumber()).isEqualTo(999);
		arepository.delete(apartment);
		brepository.delete(building);
	}

	@Test
	public void deleteNewApartmentTest() {
		brepository.save(building);
		arepository.save(apartment);
		List<Apartment> apartments = arepository.findByOwnerSurname("Duck");
		assertThat(apartments).hasSize(1);
		Apartment mockApartment = apartments.get(0);
		arepository.delete(mockApartment);
		List<Apartment> newApartments = arepository.findByOwnerSurname("Duck");
		assertThat(newApartments).hasSize(0);
		brepository.delete(building);

	}
}
