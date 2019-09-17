package ca.project.service;

import java.util.List;

import org.springframework.stereotype.Service;


import ca.project.model.Users;

import ca.project.repository.UserRepository;

@Service
public class UserService {
private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<Users> findAllUsers() {
	
		return (List<Users>) userRepository.findAll();
	}
}
