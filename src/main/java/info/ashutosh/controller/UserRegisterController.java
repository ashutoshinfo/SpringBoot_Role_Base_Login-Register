package info.ashutosh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import info.ashutosh.service.UserService;
import info.ashutosh.transferobject.dto.UserRegistrationDto;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

	@Autowired
	UserService service;
	@Autowired
	Environment environment;

	// String property = environment.getProperty("user.dir") + "\\MyPhotos";

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
	public ModelAndView registerUserAccount(@ModelAttribute("newUser") @Valid UserRegistrationDto dto, BindingResult result, @RequestParam("image") MultipartFile multipartFile) {
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
			dto.setImage(multipartFile);
			// Need Transaction ! for Below Two method Call
			service.registerUser(dto);
			service.saveImage(multipartFile);
			modelAndView.setViewName("redirect:/login?success");
		}

		return modelAndView;
	}

}
