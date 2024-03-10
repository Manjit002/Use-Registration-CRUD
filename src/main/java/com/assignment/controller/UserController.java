package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.User;
import com.assignment.service.UserService;

@RestController
@RequestMapping("/assignment")
public class UserController {
	@Autowired
	UserService usrService;

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser( @RequestBody String userData) {
		User usr = usrService.registerUser(userData);
		if (usr != null) {
			return ResponseEntity.ok("User With EmailId  " + usr.getEmail() + "  Register SuccessFully");
		} else
			return ResponseEntity.ok("User Existed With Email");
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllRegisterUser() {
		List<User> userList = usrService.getAllUser();
		if (!userList.isEmpty()) {
			return ResponseEntity.ok(userList);
		} else
			return ResponseEntity.ok("No registered user are there");
	}

	@GetMapping("/loginByEmailAndPassword")
	public ResponseEntity<?> loginUserByEmailAndPassword( @RequestParam("email") String email,
			@RequestParam("password") String password) {
		User usr = usrService.userLoginUsingEmailAndPassword(email, password);
		if (usr != null) {
			return ResponseEntity.ok(usr);
		} else
			return ResponseEntity.ok("No User Is Present With The Email");
	}

	@DeleteMapping("/deleteByEmail")
	public ResponseEntity<?> deleteUserByEmail(@RequestParam("email") String email) {
		User usr = usrService.deletUserByEmail(email);
		if (usr != null) {
			return ResponseEntity.ok("User with email_id  " + usr.getEmail() + "  deleted Successfully");
		} else
			return ResponseEntity.ok("No such Record is present");
	}

	public ResponseEntity<?> editUser( @RequestBody User usr,
			@RequestParam(value = "id", required = true) Integer id) {
		User user = usrService.editUser(usr, id);
		if (user != null) {
			return ResponseEntity.ok("user update successfully");
		} else
			return ResponseEntity.ok("Enter valid details");
	}
}
