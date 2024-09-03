package com.ani.springmvcboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.springmvcboot.payloads.UserDto;
import com.ani.springmvcboot.security.JwtHelper;
import com.ani.springmvcboot.security.JwtRequest;
import com.ani.springmvcboot.security.JwtResponse;
import com.ani.springmvcboot.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name="AuthController", description="API for authentication")
public class AuthController {

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
	
    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/login/")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
    	return "Invalid email or password";
    }
    
    @PostMapping("/register/")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
    	
    	
    	UserDto registeredUser = this.userService.registerNewUser(userDto);
    	
    	return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    	
    }
    
    

    
}
