package com.example.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserModel;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.validator.UserValidator;

@RestController
@RequestMapping(value = "/api/user")
public class UserApi {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
//	@Autowired
//	private UserValidator userValidator;

	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody long[] ids) {
		if (ids.length > 0) {
			for (long item : ids) {
				userRepository.delete(item);
			}
		}
		return ResponseEntity.ok("success");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody @Valid UserModel userModel, @PathVariable("id") long id,BindingResult bindingResult) {
		//userValidator.validate(userModel, bindingResult);
		if (bindingResult.hasErrors()) {
			return ResponseEntity.ok(bindingResult.getFieldErrors());
		} else {
			return ResponseEntity.ok(userService.update(userModel, id));
		}
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody @Valid UserModel userModel, BindingResult bindingResult) {
		//userValidator.validate(userModel, bindingResult);
		if (bindingResult.hasErrors()) {
			return ResponseEntity.ok(bindingResult.getFieldErrors());
		} else {
			return ResponseEntity.ok(userService.save(userModel));
		}

	}
	@PostMapping("/update-role/{id}")
	public ResponseEntity<UserModel> updateRole(@RequestBody UserModel userModel,@PathVariable("id") long id) {		
		return ResponseEntity.ok(userService.updateRoleUser(userModel, id));
	}

}
