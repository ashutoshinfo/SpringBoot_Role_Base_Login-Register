package info.ashutosh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView registerUserAccount(@ModelAttribute("newUser") @Valid UserRegistrationDto dto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.setViewName("registration");
			// return modelAndView;
		}

		boolean[] errorCode = service.checkUserAndEmailExistence(dto);
		boolean hasError = false;

		if (errorCode[2] == true || errorCode[0] == true) {
			modelAndView.addObject("Email", "Email");
			modelAndView.addObject("input", "form-control is-invalid");
			modelAndView.setViewName("registration");
			hasError = true;
		}

		if (errorCode[2] == true || errorCode[1] == true) {
			modelAndView.addObject("Username", "Username");
			modelAndView.addObject("usernameinput", "form-control is-invalid");
			modelAndView.setViewName("registration");
			hasError = true;
		}

		if (!hasError & !result.hasErrors()) {
			service.registerUser(dto);
			modelAndView.setViewName("redirect:/login?success");
		}
		return modelAndView;
	}

}
