package com.rashika.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

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
			@RequestParam("Password") String password,ModelMap map,HttpSession session) throws ServiceException {
	
		System.out.println("TicketController-> register-  "+",emailid=" + emailId + ",password:" + password);

			
				try {
					ticketservice.login(emailId, password);
					session.setAttribute("LOGGED_IN", emailId);
				} catch (PersistanceException e) {
					map.addAttribute("ERROR", e.getMessage());
					LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
					return "../Login.jsp";
				}
				
			
			return "redirect:../userpage.jsp";
	}
	
	@GetMapping("/create_ticket")
	public String createTicket(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("Subject") String subject, @RequestParam("Description") String description,
			@RequestParam("Department") String department, @RequestParam("Priority") String priority,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController->create ticket-EmailId" + emailId + ",Password:" + password + ",Subject"
				+ subject + ",Description:" + description + ",Department:" + department + description + ",Priority:"
				+ priority);
		TicketService ticketService = new TicketService();

		try {
			ticketService.createTicket(emailId, password, subject, description, department, priority);
			return "redirect:../createdTicket.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Ticket Creation Failed Exception Occured!!", e);
			return "redirect:../createTicket.jsp";

		}

	}
	@GetMapping("/update_ticket")
	public String updateTicket(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password, @RequestParam("IssueId") int issueId,
			@RequestParam("UpdateDescription") String updateDescription,ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",IssueId" + issueId + ",Description:" + updateDescription);
		TicketService ticketService = new TicketService();

		try {
			ticketService.updateTicket(emailId, password, issueId, updateDescription);
			return "redirect:../description.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Updating Description Exception Occured!!", e);
			return "../updateTicket.jsp";

		}

	}

	@GetMapping("/update_close")
	public String updateClose(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password, @RequestParam("IssueId") int issueId,ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",IssueId" + issueId);
		TicketService ticketService = new TicketService();

		try {
			ticketService.updateClose(emailId, password, issueId);
			return "redirect:../closedTicket.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Closing TicketException Occured!!", e);
			return "../closeTicket.jsp";

		}

	}

	
}
