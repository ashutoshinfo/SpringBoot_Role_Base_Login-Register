package info.ashutosh.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController implements ErrorController {

//	@RequestMapping("/error")
//	public String handleErrors() {
//		// do something like logging
//		return "404";
//	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				
				return "redirect:login";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "403";
			}
		}
		return "error";
	}

}
