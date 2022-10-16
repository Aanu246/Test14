package com.eazyschool.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eazyschool.model.Address;
import com.eazyschool.model.Person;
import com.eazyschool.model.Profile;
import com.eazyschool.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	@Autowired
	PersonRepository personRepository;
	
	
	@RequestMapping("/displayProfile")
	public ModelAndView displayMessages(Model model, HttpSession session){
		Person person = (Person) session.getAttribute("LoggedInPerson");
		Profile profile = new Profile();
		profile.setName(person.getName());
		profile.setMobileNumber(person.getMobileNumber());
		profile.setEmail(person.getEmail());
		if(person.getAddress() != null && person.getAddress().getAddressId()>0) {
			profile.setAddress1(person.getAddress().getAddress1());
			profile.setAddress2(person.getAddress().getAddress2());
			profile.setCity(person.getAddress().getCity());
			profile.setState(person.getAddress().getState());
			profile.setZipCode(person.getAddress().getZipCode());
		}
		
		ModelAndView modelAndView = new ModelAndView("profile.html");
		modelAndView.addObject("profile",profile);
		return modelAndView;
	}
	
	@PostMapping(value="/updateProfile")
	public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
			HttpSession session) {
		if(errors.hasErrors()) {
			return "profile.html";
		}
		
		Person person = (Person) session.getAttribute("LoggedInPerson");
		profile.setName(profile.getName());
		profile.setMobileNumber(profile.getMobileNumber());
		profile.setEmail(profile.getEmail());
		if(person.getAddress() == null || !(person.getAddress().getAddressId()>0)) {
			person.setAddress(new Address());
		}	
			person.getAddress().setAddress1(profile.getAddress1());
			person.getAddress().setAddress2(profile.getAddress2());
			person.getAddress().setCity(profile.getCity());
			person.getAddress().setState(profile.getState());
			person.getAddress().setZipCode(profile.getZipCode());
			personRepository.save(person);
			session.setAttribute("LoggedInPeron",person);
			return "redirect:/displayProfile";
		
		
	}

}
