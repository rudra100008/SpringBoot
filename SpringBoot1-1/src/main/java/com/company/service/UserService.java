package com.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.dao.UserRepository;
import com.company.entity.User;

@Service
public class UserService {
 
	@Autowired
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
	public List<User> readUser() {
		List<User> users=new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		 return users;
	}
	public void deleteUser(int id) {
		this.userRepository.deleteById(id);
	}
}
