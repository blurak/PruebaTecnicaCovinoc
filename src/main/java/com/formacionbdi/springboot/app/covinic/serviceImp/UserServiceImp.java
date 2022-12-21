package com.formacionbdi.springboot.app.covinic.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.formacionbdi.springboot.app.covinic.models.User;
import com.formacionbdi.springboot.app.covinic.repository.UserRepository;
import com.formacionbdi.springboot.app.covinic.service.IUserService;

@Service
public class UserServiceImp implements IUserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<?> crear(User user,BindingResult result) {
		// TODO Auto-generated method stub
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El Campo " + err.getField() + " " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		Optional<User> userOptional = userRepo.findById(user.getIdentificacion());;
		if(userOptional.isPresent()) {
			response.put("Mensaje", "Ya existe un usuario con esa identificacion");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			User p = userRepo.save(user);
			if (p != null) {
				response.put("Mensaje", "El usuario fue creado con exito");
				response.put("Usuario", p);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

			} else {
				response.put("Mensaje", "Error al crear El usuario");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.put("Mensaje", "Compruebe Datos no diligenciados");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
		
	

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		Optional<User> p = userRepo.findById( id);
		Map<String, Object> response = new HashMap<>();
		if (p.isPresent()) {
			userRepo.delete(p.get());
		}else {					
			response.put("Mensaje", "El usuario que desea eliminar no se encuentra");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("Mensaje", "El usuario se elimino con exito");
		response.put("Usuario",p.get());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<?> Update(User user, BindingResult result) {
		Optional<User> p = userRepo.findById( user.getIdentificacion());
		
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El Campo " + err.getField() + " " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		if (p.isPresent()) {
			User userResponse= userRepo.save(user);
			response.put("Mensaje", "El usuario se editado con exito");
			response.put("Usuario",userResponse);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}else {					
			response.put("Mensaje", "El usuario que desea editar no se encuentra");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
	}



	@Override
	public ResponseEntity<?> findById(Long id) {
		Optional<User> p = userRepo.findById(id);
		Map<String, Object> response = new HashMap<>();
		if(p.isPresent()) {			
			
			response.put("Usuario",p.get());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		response.put("Mensaje", "El usuario no se encuentra");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		
		
	}



	@Override
	public ResponseEntity<?> findAll() {
		List<User> userList = userRepo.findAll();
		
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

}
