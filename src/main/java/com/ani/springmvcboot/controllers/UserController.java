package com.ani.springmvcboot.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.springmvcboot.payloads.UserDto;
import com.ani.springmvcboot.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.ani.springmvcboot.payloads.ApiResponse;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserService userService;
	
	//POST - Create new user
	
	@PostMapping("/")
	@Tag(name="UserController")
	@Operation(summary="Create new user")
	@Hidden
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//PUT - Update existing user
	
	@PutMapping("/{userId}")
	@Operation(summary="Update details of existing user")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId){
		UserDto updateUserDto = this.userService.updateUser(userDto, uId);
		return new ResponseEntity<>(updateUserDto, HttpStatus.OK);
	}
	
	// DELETE - Delete existing user
	
	
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary="Delete existing user")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity(new ApiResponse("User Deleted",true),HttpStatus.OK);
	}
	
	// GET - Get user
	
	@GetMapping("/")
	@Operation(summary ="Get all users", tags= {"user-controller"})
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// GET - Single user
	
	@GetMapping("/{userId}")
	@Operation(summary ="Get a single user from id", tags= {"user-controller"})

	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid){
		return ResponseEntity.ok(this.userService.getUserById(uid));
	}
	
}
