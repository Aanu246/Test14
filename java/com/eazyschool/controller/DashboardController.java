package com.eazyschool.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazyschool.model.Person;
import com.eazyschool.repository.PersonRepository;

@Controller
public class DashboardController {
	
	@Autowired
	PersonRepository personRepository;
	@RequestMapping("/dashboard")
	public String displayDashboard(Model model, Authentication authentication,HttpSession session) {
		Person person = personRepository.readByEmail(authentication.getName());
		model.addAttribute("username",person.getName());
		model.addAttribute("roles",authentication.getAuthorities().toString());
		if(null != person.getEazyClass() && null != person.getEazyClass().getName()) {
			model.addAttribute("enrolledClass", person.getEazyClass().getName());
		}
		session.setAttribute("LoggedInPerson",person);
		//throw new RuntimeException("it's been a bad day");
		return "dashboard.html";
	}

}
