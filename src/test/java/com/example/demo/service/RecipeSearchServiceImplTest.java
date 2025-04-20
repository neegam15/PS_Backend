//package com.example.demo.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import com.example.demo.entity.Recipe;
//import com.example.demo.exception.RecipeNotFoundException;
//import com.example.demo.repository.RecipeRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class RecipeSearchServiceImplTest {
//
//	@Mock
//	private RecipeRepository recipeRepository;
//
//	@InjectMocks
//	private RecipeSearchServiceImpl recipeSearchService;
//
//	@Test
//	void getRecipeById_ExistingId_ReturnsRecipe() {
//		Long id = 1L;
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
//		when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));
//
//		ResponseEntity<Recipe> response = recipeSearchService.getRecipeById(id);
//
//		assertEquals(1, response.getBody().getId());
//		assertEquals("Classic Margherita Pizza", response.getBody().getName());
//	}
//
//	@Test
//	void getRecipeById_NotFound_ThrowsException() {
//		Long id = 99L;
//
//		when(recipeRepository.findById(id)).thenReturn(Optional.empty());
//
//		assertThrows(RecipeNotFoundException.class, () -> recipeSearchService.getRecipeById(id));
//	}
//}
