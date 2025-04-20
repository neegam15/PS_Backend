package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.RecipeDropDownDTO;
import com.example.demo.entity.Recipe;

public interface RecipeSearchService {

	void loadRecipes(List<Recipe> recipes);
	
	List<RecipeDropDownDTO> searchRecipesByPartialKeyword(String keyword);

	ResponseEntity<Recipe> getRecipeById(Long id);
}
