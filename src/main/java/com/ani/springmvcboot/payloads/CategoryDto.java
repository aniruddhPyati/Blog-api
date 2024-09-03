package com.ani.springmvcboot.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {


	private Integer categoryId;
	
	@NotEmpty
	private String categoryTitle;
	
	@Size(min=10, max=100, message ="Description must have a minimum of 10 characters and a maximum of 100 characters")
	private String categoryDescription;
}
