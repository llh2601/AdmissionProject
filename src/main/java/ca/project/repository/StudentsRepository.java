package ca.project.repository;

import org.springframework.data.repository.CrudRepository;

import ca.project.model.Student;

public interface StudentsRepository extends CrudRepository<Student, Integer> {

}
