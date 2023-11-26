package com.backendprogramming.projectbuilding.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;

@Controller
public class ProjectBuildingController {
	@Autowired
	private AppUserRepository urepository;

	// Login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Signup page form
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpForm(Model model) {
		model.addAttribute("appUser", new AppUser());
		return "register";
	}

	// Save new user

	@RequestMapping(value = "/saveAppUser", method = RequestMethod.POST)
	public String addAppUser(@RequestParam("password") String password, AppUser appUser) {
		// encrypt password and set its
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		appUser.setPasswordHash(hashedPassword);
		// set role
		appUser.setRole("USER");
		urepository.save(appUser);
		return "redirect:/login";
	}

}
