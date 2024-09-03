package com.ani.springmvcboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ani.springmvcboot.entities.Post;
import com.ani.springmvcboot.payloads.PostDto;
import com.ani.springmvcboot.payloads.PostResponse;


public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId );
	void deletePost(Integer postId);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostsByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);

}
