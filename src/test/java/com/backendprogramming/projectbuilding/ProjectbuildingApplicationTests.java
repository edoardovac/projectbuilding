package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendprogramming.projectbuilding.web.ApartmentController;
import com.backendprogramming.projectbuilding.web.BuildingController;
import com.backendprogramming.projectbuilding.web.DocumentController;
import com.backendprogramming.projectbuilding.web.ProjectBuildingController;
import com.backendprogramming.projectbuilding.web.ProjectBuildingRestController;
import com.backendprogramming.projectbuilding.web.UserController;

@SpringBootTest
class ProjectbuildingApplicationTests {

	@Autowired
	private ProjectBuildingController controller;
	@Autowired
	private BuildingController bcontroller;
	@Autowired
	private ApartmentController acontroller;
	@Autowired
	private DocumentController dcontroller;
	@Autowired
	private UserController ucontroller;
	@Autowired
	private ProjectBuildingRestController pbrcontroller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	void buildingContextLoads() throws Exception {
		assertThat(bcontroller).isNotNull();
	}
	
	@Test
	void apartmentContextLoads() throws Exception {
		assertThat(acontroller).isNotNull();
	}
	
	@Test
	void documentContextLoads() throws Exception {
		assertThat(dcontroller).isNotNull();
	}
	
	@Test
	void userContextLoads() throws Exception {
		assertThat(ucontroller).isNotNull();
	}
	
	@Test
	void restContextLoad() throws Exception {
		assertThat(pbrcontroller).isNotNull();
	}

}
