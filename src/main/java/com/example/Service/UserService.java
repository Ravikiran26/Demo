package com.example.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Users;
import com.example.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void saveUser(Users user) {
		userRepository.save(user);
	}
	
	public List<Users> findAll(){
		return userRepository.findAll();
	}
	
	public Users findById(int id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteById(int id) {
		 userRepository.deleteById(id);
	}

}
