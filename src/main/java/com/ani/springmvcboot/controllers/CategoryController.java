package com.ani.springmvcboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.springmvcboot.payloads.ApiResponse;
import com.ani.springmvcboot.payloads.CategoryDto;
import com.ani.springmvcboot.payloads.UserDto;
import com.ani.springmvcboot.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// POST - Create new category
	@PostMapping("/")
	@Operation(summary ="Create category", tags= {"category-controller"})

	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	// PUT - Update existing user
	
	@PutMapping("/{categoryId}")
	@Operation(summary ="Update existing category", tags= {"category-controller"})

	public ResponseEntity<CategoryDto> updateCateogry(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer catId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	// DELETE - Delete existing user
	
		@DeleteMapping("/{categoryId}")
		@Operation(summary ="Delete existing category", tags= {"category-controller"})

		public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("categoryId") Integer catid){
			this.categoryService.deleteCategory(catid);
			return new ResponseEntity(new ApiResponse("Category Deleted",true),HttpStatus.OK);
		}
		
		// GET - Get categories
		
		@GetMapping("/")
		@Operation(summary ="Get all categories", tags= {"category-controller"})

		public ResponseEntity<List<CategoryDto>> getAllCategories(){
			return ResponseEntity.ok(this.categoryService.getCategories());
		}
		
		// GET - Single category
		
		@GetMapping("/{categoryId}")
		@Operation(summary ="Get single category", tags= {"category-controller"})

		public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") Integer catid){
			return ResponseEntity.ok(this.categoryService.getCategory(catid));
		}

}
