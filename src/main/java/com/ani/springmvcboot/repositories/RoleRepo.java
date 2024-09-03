package com.ani.springmvcboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.springmvcboot.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
