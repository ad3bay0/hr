/**
 * 
 */
package com.ad3bay0.dev.hr.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ad3bay0.dev.hr.model.User;
import com.ad3bay0.dev.hr.service.UserService;

/**
 * @author Adebayo Adeniyan 1 Jun 2017
 */
@Controller
public class LoginController {
	
	private static final Logger log =  Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");

		return modelAndView;

	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		
		log.info("registration GET");

		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		
		log.info("registration GET END");

		return modelAndView;

	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		log.info("registration POST");

		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {

			bindingResult.rejectValue("email", "error.user", "There is already a user with this email provided");

		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		log.info("registration GET POST");
		return modelAndView;
	}

	@RequestMapping(value="/secure/home",method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findUserByEmail(auth.getName());
		
		modelAndView.addObject("userName","Welcome "+user.getFirstName()+" "+user.getLastName());
		
		modelAndView.addObject("adminMessage","Content Available ony for users with Admin Role");
		
		modelAndView.setViewName("secure/home");

		return modelAndView;

	}

}
