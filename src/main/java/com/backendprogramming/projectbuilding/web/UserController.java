package com.backendprogramming.projectbuilding.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backendprogramming.projectbuilding.domain.ApartmentRepository;
import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;
import com.backendprogramming.projectbuilding.domain.BuildingRepository;

@Controller
public class UserController {
	@Autowired
	private BuildingRepository brepository;
	@Autowired
	private ApartmentRepository arepository;
	@Autowired
	private AppUserRepository urepository;
	
	// show all users with role USER
	@RequestMapping(value = "/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showUsers(Model model) {
		
		// model.addAttribute("users", urepository.findByRole("USER"));
		model.addAttribute("users", urepository.findByRole("USER"));
		return "userlist";
	}
	
	// edit page for users with role USER
	@RequestMapping(value= "/edituser/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editUser(@PathVariable("id") Long appUserId, Model model) {
		AppUser appUser = urepository.findById(appUserId).get();
		model.addAttribute("user", appUser);
		model.addAttribute("building", brepository.findAll());
		model.addAttribute("apartment", arepository.findAll());		
		return "edituser";
	}
	
	// save user with role USER
	@RequestMapping(value="/saveuser", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveEditUser(AppUser givenAppUser) {
		Long appUserId = givenAppUser.getId();
		AppUser appUser = urepository.findById(appUserId).get();
		givenAppUser.setPasswordHash(appUser.getPasswordHash());
		givenAppUser.setRole(appUser.getRole());
		urepository.save(givenAppUser);
		return "redirect:/users";	
	}
}
