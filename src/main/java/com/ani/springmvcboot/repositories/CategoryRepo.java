package com.ani.springmvcboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.springmvcboot.entities.Category;



public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
}
