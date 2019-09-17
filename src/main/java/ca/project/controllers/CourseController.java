package ca.project.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.project.model.Course;
import ca.project.model.Result;
import ca.project.repository.CourseRepository;
import ca.project.repository.ResultRepository;


@Controller
@RequestMapping(value="/courses")
public class CourseController {
	@Autowired
	CourseRepository repo;
	@Autowired
	ca.project.service.CourseService service;
	@Autowired
	ResultRepository resul;
	
	@GetMapping("/findAll")
	public String List(Model model) {
        List<Course> courseList = (List<Course>) service.findAllCourses();
        model.addAttribute("courses", courseList);
		return "courses/coursesList";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idCourse, Model model) {		
		Optional<Course> course =service.findCourseById(idCourse);			
		model.addAttribute("course", course);
		
		return "courses/formCourse";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCourse, RedirectAttributes attributes) {		
		
		//System.out.println("ok");
		boolean val=true;
		for(Result res : resul.findAll())
		{
		    
		    if(res.getCourse().getId()==idCourse)
		    	val=false;
		    break;
		    	
		}
		if(val==true) {
			service.deleteCourse(idCourse);	
		attributes.addFlashAttribute("msg", "Deleted!.");
		return "redirect:/courses/findAll";
		}else {
			attributes.addFlashAttribute("msg", "can not Deleted!.");
			return "redirect:/courses/findAll";	
			
		}
	}
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Course course) {
		return "courses/formCourse";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Course course, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Errors");
			return "courses/formCourse";
		}	
		
	
		service.saveCourse(course);
		attributes.addFlashAttribute("msg", "saved!");		
		return "redirect:/courses/findAll";
	}
	
	
	
	
	
	
}
