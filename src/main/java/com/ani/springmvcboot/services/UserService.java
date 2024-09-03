package com.ani.springmvcboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ani.springmvcboot.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
	UserDto registerNewUser(UserDto user);
	
	
}
