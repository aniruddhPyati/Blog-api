package com.ani.springmvcboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ani.springmvcboot.entities.Category;
import com.ani.springmvcboot.entities.Post;
import com.ani.springmvcboot.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {


	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
