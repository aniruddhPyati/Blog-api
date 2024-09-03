package com.ani.springmvcboot.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ani.springmvcboot.config.AppConstants;
import com.ani.springmvcboot.entities.Role;
import com.ani.springmvcboot.entities.User;
import com.ani.springmvcboot.payloads.UserDto;
import com.ani.springmvcboot.repositories.RoleRepo;
import com.ani.springmvcboot.repositories.UserRepo;
import com.ani.springmvcboot.services.UserService;
import com.ani.springmvcboot.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
	
		User user = this.dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).
				    orElseThrow(()-> new ResourceNotFoundException
				    		("User","id",userId));
		user.setName(userDto.getName());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		 if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
	            user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encode password
	        }
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}
	
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(newUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).
				    orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
	List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).
				    orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
       this.userRepo.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {  // Dto to Entity
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userToDto(User user) {  // Entity to Dto
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

	

}
