 package com.ani.springmvcboot.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ani.springmvcboot.entities.Category;
import com.ani.springmvcboot.exceptions.ResourceNotFoundException;
import com.ani.springmvcboot.payloads.CategoryDto;
import com.ani.springmvcboot.repositories.CategoryRepo;
import com.ani.springmvcboot.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				             .orElseThrow(()-> new ResourceNotFoundException("Category","category id", categoryId));

		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return this.CategoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				            .orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
	    category.setCategoryId(categoryDto.getCategoryId());
	    
	    Category updatedCategory = this.categoryRepo.save(category);
	    return this.CategoryToDto(updatedCategory);
		
	
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				            .orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		
		return this.CategoryToDto(category);
	}

	@Override
	public List<CategoryDto> getCategories() {
	
		List<Category> categories = this.categoryRepo.findAll()	;	
		List<CategoryDto> categoryDtos = categories.stream().map(category->this.CategoryToDto(category)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto CategoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
