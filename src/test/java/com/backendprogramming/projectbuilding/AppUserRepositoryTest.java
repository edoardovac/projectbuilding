package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;

@SpringBootTest
public class AppUserRepositoryTest {
	@Autowired
	private AppUserRepository urepository;
	
	private AppUser appUser = new AppUser("testusername",
			"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "PROVA");

	@Test
	public void createNewAppUserTest() {
		urepository.save(appUser);
		assertThat(appUser.getId()).isNotNull();
		assertThat(urepository.findById(appUser.getId())).isNotNull();
		urepository.delete(appUser);
	}

	@Test
	public void findByUsernameShouldReturnAppUser() {
		urepository.save(appUser);
		AppUser newAppUsers = urepository.findByUsername("testusername");
		assertThat(newAppUsers.getUsername()).isEqualTo("testusername");
		urepository.delete(appUser);
	}

	@Test
	public void findByRoleShouldReturnAppUsers() {
		urepository.save(appUser);
		List<AppUser> appUsers = urepository.findByRole("PROVA");
		assertThat(appUsers).hasSizeGreaterThan(0);
		assertThat(appUsers.get(0).getRole()).isEqualTo("PROVA");
		urepository.delete(appUser);
	}

	@Test
	public void deleteAppUserTest() {
		urepository.save(appUser);
		AppUser mockAppUser = urepository.findByUsername("testusername");
		assertThat(mockAppUser).isNotNull();
		urepository.delete(appUser);
		AppUser newAppUsers = urepository.findByUsername("testusername");
		assertThat(newAppUsers).isNull();

	}

}
