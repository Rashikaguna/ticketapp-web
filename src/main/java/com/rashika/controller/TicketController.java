package com.rashika.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rashika.dao.UserInformationDAO;
import com.rashika.exception.PersistanceException;
import com.rashika.exception.ServiceException;
import com.rashika.exception.ValidatorException;
import com.rashika.model.Employee;
import com.rashika.model.TicketIssue;
import com.rashika.model.UserInformation;
import com.rashika.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	private static final Logger LOGGER = Logger.getLogger(TicketController.class.getName());
	TicketService ticketservice = new TicketService();

	@GetMapping
	public String index(ModelMap modelmap) throws ServiceException {
		System.out.println("department->index");
		return "index.jsp";
	}

	@GetMapping("/register")
	public String registerNewUser(@RequestParam("Name") String name, @RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password) throws ServiceException {

		System.out
				.println("TicketController-> register- name:" + name + ",emailid=" + emailId + ",password:" + password);
		TicketService ticketService = new TicketService();
		try {
			ticketService.registration(name, emailId, password);
			return "redirect:../index.jsp";

		} catch (ServiceException e) {

			LOGGER.log(Level.SEVERE, "Registration Failed Exception Occurred!!", e);
			return "register.jsp";

		}

	}

	@GetMapping("/user_login")
	public String userLogin(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			ModelMap map, HttpSession session) throws ServiceException {

		System.out.println("TicketController-> register-  " + ",emailid=" + emailId + ",password:" + password);
		UserInformation user = new UserInformation();
		user.setEmailId(emailId);
		user.setPassword(password);
		try {
			ticketservice.login(emailId, password);
			session.setAttribute("LOGGED_IN_USER", user);
			return "redirect:../userpage.jsp";
		} catch (PersistanceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Login Failed Exception Occured!!", e);
			return "../userlogin.jsp";
		}

	}

	@GetMapping("/create_ticket")
	public String createTicket(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("Subject") String subject, @RequestParam("Description") String description,
			@RequestParam("Department") String department, @RequestParam("Priority") String priority, ModelMap map)
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
	public String updateTicket(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("TicketId") int ticketId, @RequestParam("UpdateDescription") String updateDescription,
			ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",TicketId" + ticketId + ",Description:" + updateDescription);
		TicketService ticketService = new TicketService();

		try {
			ticketService.updateTicket(emailId, password, ticketId, updateDescription);
			return "redirect:../description.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Updating Description Exception Occured!!", e);
			return "../updateTicket.jsp";

		}

	}

	@GetMapping("/update_close")
	public String updateClose(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("TicketId") int ticketId, ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",TicketId" + ticketId);
		TicketService ticketService = new TicketService();

		try {
			ticketService.updateClose(emailId, password, ticketId);
			return "redirect:../closedTicket.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Closing TicketException Occured!!", e);
			return "../closeTicket.jsp";
		}
	}

	@GetMapping("/view_user_tickets")
	public String findUserDetails(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password);
		TicketService ticketService = new TicketService();
		try {
			TicketIssue issue = new TicketIssue();

			UserInformation user = new UserInformation();
			UserInformationDAO userInformationDAO = new UserInformationDAO();
			user.setId(userInformationDAO.findId(emailId).getId());

			issue.setUserId(user);
			List<TicketIssue> i = ticketService.findUserDetails(issue);
			map.addAttribute("list", i);
			return "../viewusertickets.jsp";
		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Viewing  Ticket Exception Occured!!", e);
			return "../userpage.jsp";

		}
	}

	@GetMapping("/employee_login")
	public String employeeLogin(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			ModelMap map, HttpSession session) throws ServiceException {

		System.out.println("TicketController-> register-  " + ",emailid=" + emailId + ",password:" + password);
		Employee employee = new Employee();
		employee.setEmailId(emailId);
		employee.setPassword(password);
		try {
			ticketservice.emplogin(emailId, password);
			session.setAttribute("LOGGED_IN_EMPLOYEE", employee);
		} catch (PersistanceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Login Failed Exception Occured!!", e);
			return "../index.jsp";
		}

		return "redirect:../employeePage.jsp";
	}

	@GetMapping("/assign_employee")
	public String assignEmployee(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("TicketId") int ticketId, @RequestParam("EmployeeId") int employeeId, ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",TicketId:0" + ticketId + ",EmployeeId:" + employeeId);
		TicketService ticketService = new TicketService();

		try {
			ticketService.assignEmployee(emailId, password, ticketId, employeeId);
			return "redirect:../assignEmployee.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Assigning Employee  Exception Occured!!", e);
			return "../userpage.jsp";

		}

	}

	@GetMapping("/delete_ticket")
	public String deleteTicket(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("TicketId") int ticketId, ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",TicketId:0" + ticketId);
		TicketService ticketService = new TicketService();

		try {
			ticketService.deleteTickets(emailId, password, ticketId);
			return "redirect:../deletedTicket.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Deleting Ticket Exception Occured!!", e);
			return "../employeePage.jsp";

		}

	}

	@GetMapping("/view_employee_tickets")
	public String findEmployeeTickets(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password, ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password);
		TicketService ticketService = new TicketService();

		try {

			List<TicketIssue> i = ticketService.findEmployeeTickets(emailId, password);
			map.addAttribute("list", i);
			return "../viewEmployeetickets.jsp";
		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Viewing  Ticket Exception Occured!!", e);
			return "../employeePage.jsp";

		}

	}

}
