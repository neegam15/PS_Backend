//package com.example.demo.controller;
//
//import com.example.demo.dto.RecipeDropDownDTO;
//import com.example.demo.entity.Recipe;
//import com.example.demo.service.RecipeSearchService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(RecipeController.class)
//public class RecipeControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private RecipeSearchService recipeSearchService;
//
//	@Test
//	void testSearchRecipesByPartialText() throws Exception {
//		// Mock data
//		RecipeDropDownDTO recipeDTO = new RecipeDropDownDTO(1L, "Classic Margherita Pizza", "Italian");
//		Mockito.when(recipeSearchService.searchRecipesByPartialKeyword(anyString()))
//				.thenReturn(Arrays.asList(recipeDTO));
//
//		mockMvc.perform(get("/api/recipes/searchPartial").param("query", "piz").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(1))
//				.andExpect(jsonPath("$[0].id").value(1L))
//				.andExpect(jsonPath("$[0].name").value("Classic Margherita Pizza"))
//				.andExpect(jsonPath("$[0].cuisine").value("Italian"));
//	}
//
//	@Test
//	void testGetRecipeById() throws Exception {
//		// Mock data
//		Recipe recipe = new Recipe();
//		recipe.setId(1L);
//		recipe.setName("Classic Margherita Pizza");
//		recipe.setCuisine("Italian");
//		recipe.setImage("https://cdn.dummyjson.com/recipe-images/1.webp");
//		recipe.setServings(4);
//		recipe.setDifficulty("Easy");
//		recipe.setCaloriesPerServing(300);
//		recipe.setRating(4.6);
//		recipe.setReviewCount(98);
//		recipe.setPrepTimeMinutes("20");
//		recipe.setCookTimeMinutes("15");
//		recipe.setIngredients(Arrays.asList("Pizza dough", "Tomato sauce", "Fresh mozzarella cheese",
//				"Fresh basil leaves", "Olive oil", "Salt and pepper to taste"));
//		recipe.setInstructions(Arrays.asList("Preheat the oven to 475°F (245°C).",
//				"Roll out the pizza dough and spread tomato sauce evenly.",
//				"Top with slices of fresh mozzarella and fresh basil leaves.",
//				"Drizzle with olive oil and season with salt and pepper.",
//				"Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
//				"Slice and serve hot."));
//		recipe.setTags(Arrays.asList("Pizza", "Italian"));
//		recipe.setMealType(Arrays.asList("Dinner"));
//		recipe.setUserId(166);
//
//		Mockito.when(recipeSearchService.getRecipeById(anyLong())).thenReturn(ResponseEntity.ok(recipe));
//
//		mockMvc.perform(get("/api/recipes/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.name").value("Classic Margherita Pizza"))
//				.andExpect(jsonPath("$.cuisine").value("Italian"))
//				.andExpect(jsonPath("$.image").value("https://cdn.dummyjson.com/recipe-images/1.webp"))
//				.andExpect(jsonPath("$.servings").value(4)).andExpect(jsonPath("$.difficulty").value("Easy"))
//				.andExpect(jsonPath("$.caloriesPerServing").value(300)).andExpect(jsonPath("$.rating").value(4.6))
//				.andExpect(jsonPath("$.reviewCount").value(98)).andExpect(jsonPath("$.prepTimeMinutes").value(20))
//				.andExpect(jsonPath("$.cookTimeMinutes").value(15))
//				.andExpect(jsonPath("$.ingredients.length()").value(6))
//				.andExpect(jsonPath("$.instructions.length()").value(6)).andExpect(jsonPath("$.tags.length()").value(2))
//				.andExpect(jsonPath("$.mealType.length()").value(1)).andExpect(jsonPath("$.userId").value(166));
//	}
//}
