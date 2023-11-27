package com.backendprogramming.projectbuilding.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.backendprogramming.projectbuilding.domain.AppUser;
import com.backendprogramming.projectbuilding.domain.AppUserRepository;
import com.backendprogramming.projectbuilding.domain.SignupForm;

import jakarta.validation.Valid;

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
	@RequestMapping(value = "/signup")
	public String signUpForm(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	// Save new user
	/*
	 * @RequestMapping(value = "/saveAppUser", method = RequestMethod.POST) public
	 * String addAppUser(@RequestParam("password") String password, AppUser appUser)
	 * { // encrypt password and set its String hashedPassword =
	 * BCrypt.hashpw(password, BCrypt.gensalt());
	 * appUser.setPasswordHash(hashedPassword); // set role appUser.setRole("USER");
	 * urepository.save(appUser); return "redirect:/login"; }
	 */
	@RequestMapping(value = "/savenewuser", method = RequestMethod.POST)
	public String saveAppUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				AppUser newUser = new AppUser();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				if (urepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					urepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

}
