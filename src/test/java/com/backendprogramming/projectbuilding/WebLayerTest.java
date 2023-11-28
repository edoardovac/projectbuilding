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
	@WithMockUser(username = "mario", password = "rossi", roles = { "USER", "ADMIN", "SUPER" })
	public void getBuildingsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/buildings")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getEditBuildingsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getApartmentsMockAdmin() throws Exception {
		this.mockMvc.perform(get("/editapartment/1")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getDocumentesMockAdmin() throws Exception {
		this.mockMvc.perform(get("/editdocument/1")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getAddBuildingMockAdmin() throws Exception {
		this.mockMvc.perform(get("/addbuilding")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getAddApartmentMockAdmin() throws Exception {
		this.mockMvc.perform(get("/addapartment/1")).andExpect(status().isOk());
	}

	// only ADMIN can access this endpoint
	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getAddDocumentBuildingMockAdmin() throws Exception {
		this.mockMvc.perform(get("/adddocumentbuilding/1")).andExpect(status().isOk());
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getEditBuildingsMockUser() throws Exception {
		this.mockMvc.perform(get("/editbuilding/1")).andExpect(status().is(403));
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getEditApartmentsMockUser() throws Exception {
		this.mockMvc.perform(get("/editapartment/1")).andExpect(status().is(403));
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getEditDocumentsMockUser() throws Exception {
		this.mockMvc.perform(get("/editdocument/1")).andExpect(status().is(403));
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getAddBuildingMockUser() throws Exception {
		this.mockMvc.perform(get("/addbuilding")).andExpect(status().is(403));
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getAddApartmentMockUser() throws Exception {
		this.mockMvc.perform(get("/addapartment/1")).andExpect(status().is(403));
	}

	// endpoint forbidden to non ADMIN
	@Test
	@WithMockUser(username = "super", password = "super", roles = { "USER", "SUPER" })
	public void getAddDocumentBuildingMockUser() throws Exception {
		this.mockMvc.perform(get("/adddocumentbuilding/1")).andExpect(status().is(403));
	}

	// endpoint forbidden to SUPER
	@Test
	@WithMockUser(username = "super", password = "super", authorities = "SUPER")
	public void getAddDocumentApartmentMockUser() throws Exception {
		this.mockMvc.perform(get("/adddocumentapartment/1")).andExpect(status().is(403));
	}
}
