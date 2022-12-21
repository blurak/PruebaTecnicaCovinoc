package com.formacionbdi.springboot.app.covinic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.formacionbdi.springboot.app.covinic.models.User;

public interface IUserService {
	public ResponseEntity<?> crear(User user,BindingResult result);
	public ResponseEntity<?> deleteById(Long id);
	public ResponseEntity<?> Update(User user,BindingResult result);
	public ResponseEntity<?> findById(Long id);
	public ResponseEntity<?> findAll();
}
