package info.ashutosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import info.ashutosh.service.UserService;
import info.ashutosh.transferobject.dto.UserRegistrationDto;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

	@Autowired
	UserService service;

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}

	@GetMapping
	public String showRegistrationPage(Model model) {
		model.addAttribute("newUser", new UserRegistrationDto());
		if (isAuthenticated()) {
			return "redirect:profile";
		}
		return "registration";
	}

	@PostMapping("register")
	public String registerUserAccount(@ModelAttribute("newUser") UserRegistrationDto dto) {
		service.registerUser(dto);

		return "redirect:/login?success";
	}

}
