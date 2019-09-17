package ca.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.project.model.Course;
import ca.project.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAllCourses() {
	
		return (List<Course>) courseRepository.findAll();
	}
	
	public Optional<Course> findCourseById(Integer id){
		return courseRepository.findById(id);
	}
	
	public void deleteCourse(Integer  id) {
		courseRepository.deleteById(id);
	}
	public void saveCourse(Course  course) {
		courseRepository.save(course);
	}
	
}
