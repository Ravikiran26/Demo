package com.example.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Users;
import com.example.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody Users user) {
		userService.saveUser(user);
	}
	
	@GetMapping("/findAll")
	public List<Users> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Users> findById(@PathVariable int id) {
		try {
			Users user = userService.findById(id);
			return new ResponseEntity<Users>(user,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> update(@RequestBody Users user,@PathVariable int id){
		try {
			Users existUser = userService.findById(id);
			user.setId(id);
			userService.saveUser(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	//to delete the user
	public void deleteById(@PathVariable int id) {
		userService.deleteById(id);
	}

}
