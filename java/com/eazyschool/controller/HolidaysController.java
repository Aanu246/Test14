package com.eazyschool.controller;

//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;

import com.eazyschool.model.Holiday;
import com.eazyschool.repository.HolidaysRepository;

@Controller
public class HolidaysController {
	
	@Autowired
	private HolidaysRepository holidaysRepository;
	
	
	// for path variable style
	@GetMapping("/holidays/{display}")
	public String displayHolidays(@PathVariable String display, Model model) {
		if(null !=display && display.equals("all")) {
			model.addAttribute("festival",true);
			model.addAttribute("federal",true);
		}else if(null != display && display.equals("federal")) {
			model.addAttribute("federal",true);
		}else if(null!=display && display.equals("festival")) {
			model.addAttribute("festival",true);
		}
		
		Iterable<Holiday> holidays = holidaysRepository.findAll();
		List<Holiday>holidayList= StreamSupport.stream(holidays.spliterator(),false).collect(Collectors.toList());
		Holiday.Type[] types = Holiday.Type.values();
		for(Holiday.Type type : types) {
			model.addAttribute(type.toString(),
			(holidayList.stream().filter(holiday-> holiday.getType().equals(type)).collect(Collectors.toList())));
			
		}
		//throw new RuntimeException("it's been a bad day");
		return "holidays.html";
	}

}
//List<Holiday>holidays= Arrays.asList(
	//	new Holiday("Jan 1","New Year's Day",Holiday.Type.FESTIVAL),
		//new Holiday(" Oct 31", "Halloween",Holiday.Type.FESTIVAL),
	//	new Holiday("Nov 24"," Thankgiving Day",Holiday.Type.FESTIVAL),
		//new Holiday("Dec 25", " Christmas", Holiday.Type.FESTIVAL),
		//new Holiday(" Jan 17","Martin Luther King jr", Holiday.Type.FEDERAL),
		//new Holiday(" July 4","Independence Day", Holiday.Type.FEDERAL),
		//new Holiday("Sept 5","Labor Day",Holiday.Type.FEDERAL),
	//	new Holiday("Nov 11","Veterans Day", Holiday.Type.FEDERAL)
	//);



// for query style
//@GetMapping("/holidays")
//public String displayHolidays(@RequestParam(required = false) boolean festival,
	//	@RequestParam(required=false) boolean federal, Model model) {
	//model.addAttribute("festival",festival);
	//model.addAttribute("federal",federal);
	//List<Holiday>holidays= Arrays.asList(
	//	new Holiday("Jan 1","New Year's Day",Holiday.Type.FESTIVAL),
	//	new Holiday(" Oct 31", "Halloween",Holiday.Type.FESTIVAL),
	//	new Holiday("Nov 24"," Thankgiving Day",Holiday.Type.FESTIVAL),
	//	new Holiday("Dec 25", " Christmas", Holiday.Type.FESTIVAL),
	//	new Holiday(" Jan 17","Martin Luther King jr", Holiday.Type.FEDERAL),
	//	new Holiday(" July 4","Independence Day", Holiday.Type.FEDERAL),
	//	new Holiday("Sept 5","Labor Day",Holiday.Type.FEDERAL),
	//	new Holiday("Nov 11","Veterans Day", Holiday.Type.FEDERAL)
	//);
