package com.example.demo.controller;

import com.example.demo.dto.RecipeDropDownDTO;
import com.example.demo.entity.Recipe;
import com.example.demo.service.RecipeSearchService;
import com.example.demo.service.RecipeSearchServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	private static final Logger logger = LoggerFactory.getLogger(RecipeSearchServiceImpl.class);

	@Autowired
	private RecipeSearchService searchService;

	@GetMapping("/searchPartial")
	@Operation(summary = "Search Recipes", description = "Search recipes by keyword (name or cuisine)")
	public List<RecipeDropDownDTO> searchRecipesByPartialText(@RequestParam String query) {
		logger.info("🔍 Searching recipes with: {}", query);
		return searchService.searchRecipesByPartialKeyword(query);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Recipe by ID", description = "Fetch a single recipe by its ID")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
		return searchService.getRecipeById(id);
	}

}
