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
public class WebLayerRestTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestBuildingAuthorized() throws Exception {
		this.mockMvc.perform(get("/building")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestBuildingIdAuthorized() throws Exception {
		this.mockMvc.perform(get("/building/1")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestApartmentAuthorized() throws Exception {
		this.mockMvc.perform(get("/apartment")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestApartmentIdAuthorized() throws Exception {
		this.mockMvc.perform(get("/apartment/1")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestDocumentAuthorized() throws Exception {
		this.mockMvc.perform(get("/document")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	public void getRestDocumentIdAuthorized() throws Exception {
		this.mockMvc.perform(get("/document/1")).andExpect(status().isOk());
	}

}
