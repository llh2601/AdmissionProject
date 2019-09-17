package ca.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.project.model.Course;
import ca.project.model.Users;
import ca.project.repository.CourseRepository;
import ca.project.repository.ResultRepository;
import ca.project.repository.UserRepository;

@Controller
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	UserRepository repo;
	@Autowired
	ca.project.service.UserService service;
	
	
	@GetMapping("/findAll")
	public String List(Model model) {
        List<Users> userList = (List<Users>) service.findAllUsers();
        model.addAttribute("users", userList);
		return "users/userList";
	}
}
