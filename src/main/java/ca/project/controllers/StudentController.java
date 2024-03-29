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

import ca.project.model.Result;
import ca.project.model.Student;
import ca.project.repository.ResultRepository;
import ca.project.repository.StudentsRepository;
import ca.project.service.StudentService;

@Controller
@RequestMapping(value="/students")
public class StudentController {
	
	@Autowired
	StudentsRepository repo;
	@Autowired
	ResultRepository result;
	@Autowired
	StudentService students;
	

	@GetMapping("/findAll")
	public String List(Model model) {
        List<Student> studentsList = (List<Student>) students.findAllStudents();
        model.addAttribute("students", studentsList);
		return "students/studentsList";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idStudent  , Model model) {		
		Optional<Student> student = students.findStudentById(idStudent);		
		model.addAttribute("student", student);
		//System.out.println(course);
		return "students/formStudent";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idStudent, RedirectAttributes attributes) {		
		
		//System.out.println("ok");
		boolean val=true;
		for(Result res : result.findAll())
		{
		    
		    if(res.getStudent().getId()==idStudent)
		    	val=false;
		    break;
		    	
		}
		if(val==true) {
			students.deleteStudent(idStudent);;	
		attributes.addFlashAttribute("msg", "Deleted!.");
		return "redirect:/students/findAll";
		}else {
			attributes.addFlashAttribute("msg", "can not Deleted!.");
			return "redirect:/students/findAll";
			
		}
		
	
		
	}
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Student student) {
		return "students/formStudent";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Student student, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Errors");
			return "students/formStudent";
		}	
		
	
		students.saveStudent(student);
		attributes.addFlashAttribute("msg", "Saved!");		
		return "redirect:/students/findAll";
	}
	
	
}
