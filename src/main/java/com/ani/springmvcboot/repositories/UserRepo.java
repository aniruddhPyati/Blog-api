package com.ani.springmvcboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.springmvcboot.entities.User;
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
}
