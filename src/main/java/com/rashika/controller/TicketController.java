package com.rashika.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rashika.exception.PersistanceException;
import com.rashika.exception.ServiceException;
import com.rashika.service.TicketService;


@Controller
@RequestMapping("/tickets")
public class TicketController {
	private static final Logger LOGGER = Logger.getLogger(TicketController.class.getName());
	TicketService ticketservice=new TicketService();
	
	@GetMapping
	public String index(ModelMap modelmap) throws ServiceException {
		System.out.println("department->index");
		return "index.jsp";
	}

	

	@GetMapping("/register")
	public String registerNewUser(@RequestParam("Name") String name, @RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password) throws ServiceException {

		System.out.println("TicketController-> register- name:" + name + ",emailid=" + emailId + ",password:" + password);
		TicketService ticketService = new TicketService();
		try {
			ticketService.registration(name, emailId, password);
			return "redirect:../index.jsp";

		} catch (ServiceException e) {

			LOGGER.log(Level.SEVERE, "Registration Failed Exception Occurred!!", e);
			return "register.jsp";

		}

	}
	@GetMapping("/login")
	public String registerNewUser(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password,ModelMap map) throws ServiceException {
	
		System.out.println("TicketController-> register-  "+",emailid=" + emailId + ",password:" + password);

			
				try {
					ticketservice.login(emailId, password);
				} catch (PersistanceException e) {
					map.addAttribute("ERROR", e.getMessage());
					LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
					return "../Login.jsp";
				}
				
			
			return "redirect:../MainPage.jsp";
	}


}
