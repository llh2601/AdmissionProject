package ca.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
	
	@GetMapping("/")
	public String Show(Model model) {
		
		return "Home";
	}
	@GetMapping("/login")
	public String login(Model model) {
		
		return "Home";
	}
	

}
