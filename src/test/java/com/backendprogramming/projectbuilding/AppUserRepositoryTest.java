package com.backendprogramming.projectbuilding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;

@DataJpaTest
public class AppUserRepositoryTest {
	@Autowired
	private AppUserRepository urepository;

	@Test
	public void createNewAppUserTest() {
		AppUser appUser = new AppUser("totallynewusername",
				"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "ROLE");
		urepository.save(appUser);
		assertThat(appUser.getId()).isNotNull();
		assertThat(urepository.findById(appUser.getId())).isNotNull();
	}

	@Test
	public void findByUsernameShouldReturnAppUser() {
		AppUser appUser = new AppUser("username", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
				"ROLE");
		urepository.save(appUser);
		AppUser newAppUsers = urepository.findById(appUser.getId()).get();
		assertThat(newAppUsers.getUsername()).isEqualTo("username");
	}

	@Test
	public void findByRoleShouldReturnAppUsers() {
		List<AppUser> appUsers = urepository.findByRole("USER");
		assertThat(appUsers).hasSizeGreaterThan(0);
		assertThat(appUsers.get(0).getRole()).isEqualTo("USER");
	}

	@Test
	public void deleteAppUserTest() {
		AppUser appUser = new AppUser("username", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
				"PROVA");
		urepository.save(appUser);
		List<AppUser> appUsers = urepository.findByRole("PROVA");
		assertThat(appUsers).hasSize(1);
		urepository.delete(appUser);
		List<AppUser> newAppUsers = urepository.findByRole("PROVA");
		assertThat(newAppUsers).hasSize(0);

	}

}
