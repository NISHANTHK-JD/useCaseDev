package com.example.bankManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankManagementSystem.model.User;
import com.example.bankManagementSystem.service.UserAccountService;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserAccountController {
	
	@Autowired
	public UserAccountService service;
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
	}
}
