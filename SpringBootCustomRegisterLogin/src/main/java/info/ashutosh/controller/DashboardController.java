package info.ashutosh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping
	public String showDashboardPage() {
		return "dashboard";
	}

}
