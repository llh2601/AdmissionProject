package ca.project.repository;

import org.springframework.data.repository.CrudRepository;

import ca.project.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
