package com.backendprogramming.projectbuilding;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "mario", password = "rossi", roles = {"USER","ADMIN", "SUPER"})
	public void getBuildingsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/buildings")).andExpect(status().isOk());
	}
	
	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getEditBuildingsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "super", password = "super", roles = {"USER", "SUPER"})
	public void getEditBuildingsMockUser() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().is(403));
	}
	/*
	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getApartmentsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "super", password = "super", roles = {"USER", "SUPER"})
	public void getEditApartmentsMockUser() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().is(403));
	}
	*/
}
