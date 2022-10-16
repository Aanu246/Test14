package com.eazyschool.controller;

import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eazyschool.model.Person;
//import com.eazyschool.repository.CoursesRepository;
//import com.eazyschool.repository.EazyClassRepository;
//import com.eazyschool.repository.PersonRepository;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
	
	//@Autowired
	//EazyClassRepository eazyClassRepository;
	
	//@Autowired
	//PersonRepository personRepopsitory;
	
	//@Autowired
	//CoursesRepository coursesRepository;
	
	@GetMapping("/displayCourses")
	public ModelAndView displayCourses(Model model, HttpSession session) {
		Person person = (Person) session.getAttribute("LoggedInPerson");
		ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
		modelAndView.addObject("person",person);
		return modelAndView;
	}

}
