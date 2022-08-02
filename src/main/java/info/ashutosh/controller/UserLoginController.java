package info.ashutosh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/login")
public class UserLoginController {

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			return false;
		}
		return authentication.isAuthenticated();
	}

	@GetMapping
	public ModelAndView showLoginPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("UserLoginController.showLoginPage()------" + ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());

		if (isAuthenticated()) {
			modelAndView.addObject("pathName", request.getContextPath());
			modelAndView.setViewName("redirect:profile");
			return modelAndView;
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

}