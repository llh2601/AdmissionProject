package ca.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.project.model.Student;
import ca.project.repository.StudentsRepository;

@Service
public class StudentService {
	private StudentsRepository studentsRepository;
	
	public StudentService(StudentsRepository studentRepository) {
		this.studentsRepository=studentRepository;
	}
	public List<Student> findAllStudents() {
		
		return (List<Student>) studentsRepository.findAll();
	}
	
	public Optional<Student> findStudentById(Integer id){
		return studentsRepository.findById(id);
	}
	
	public void deleteStudent(Integer  id) {
		studentsRepository.deleteById(id);
	}
	public void saveStudent(Student  student) {
		studentsRepository.save(student);
	}
	
}
