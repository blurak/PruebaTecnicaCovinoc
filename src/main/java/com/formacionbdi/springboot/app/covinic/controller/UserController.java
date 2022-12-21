package com.formacionbdi.springboot.app.covinic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.covinic.models.User;
import com.formacionbdi.springboot.app.covinic.service.IUserService;


@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@PostMapping("/newUser")
	public ResponseEntity<?> crearUser(@Valid @RequestBody User user, BindingResult result) {
		return service.crear(user, result);

	}
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
		return service.Update(user, result);

	}
	
	@GetMapping("getUser/{id}")	
	public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {		
		return service.findById(id);
	}

	
	@GetMapping("/getUser")	
	public ResponseEntity<?> getUsers() {
		
		return service.findAll();
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {		
		return service.deleteById(id);
	}
}