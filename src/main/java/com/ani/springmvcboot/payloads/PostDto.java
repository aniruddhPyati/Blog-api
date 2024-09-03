package com.ani.springmvcboot.payloads;

import java.util.Date;

import com.ani.springmvcboot.entities.Category;
import com.ani.springmvcboot.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
}
