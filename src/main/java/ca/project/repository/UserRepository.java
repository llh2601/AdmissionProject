package ca.project.repository;

import org.springframework.data.repository.CrudRepository;

import ca.project.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {

}
