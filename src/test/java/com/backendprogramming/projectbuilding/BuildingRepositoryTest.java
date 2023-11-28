package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendprogramming.projectbuilding.domain.Building;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;

@SpringBootTest
public class BuildingRepositoryTest {

	@Autowired
	private BuildingRepository brepository;
	
	private Building building = new Building("11 Downing Street", 2, 6);

	@Test
	public void createNewBuildingTest() {
		brepository.save(building);
		assertThat(building.getId()).isNotNull();
		brepository.delete(building);
	}

	@Test
	public void findByAddressTestShouldReturnBuilding() {
		brepository.save(building);
		List<Building> buildings = brepository.findByAddress("11 Downing Street");
		assertThat(buildings).hasSize(1);
		assertThat(buildings.get(0).getAddress()).isEqualTo("11 Downing Street");
		brepository.delete(building);
	}

	@Test
	public void deleteBuildingTest() {
		brepository.save(building);
		List<Building> buildings = brepository.findByAddress("11 Downing Street");
		assertThat(buildings).hasSize(1);
		Building newBuilding = buildings.get(0);
		brepository.delete(newBuilding);
		List<Building> newBuildings = brepository.findByAddress("11 Downing Street");
		assertThat(newBuildings).hasSize(0);
	}
}
