package com.ani.springmvcboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ani.springmvcboot.entities.User;
import com.ani.springmvcboot.exceptions.ResourceNotFoundException;
import com.ani.springmvcboot.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Loading user from DB by username
		
		User user =this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email: "+ username,0));
		return user;
	}

}
