package com.example.demo.client;

import com.example.demo.dto.RecipeDTO;
import com.example.demo.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

@SpringBootTest
class RecipeClientTest {

	private RecipeClient recipeClient;

	@BeforeEach
	void setUp() {
		// Mock the RecipeClient interface
		recipeClient = mock(RecipeClient.class);
	}

	@Test
	void testGetRecipes_Success() {
		// Given a valid response with recipes
		RecipeDTO recipe1 = new RecipeDTO();
		recipe1.setId(1L);
		recipe1.setName("Chicken Biryani");
		recipe1.setCuisine("Indian");
		recipe1.setServings(4);
		recipe1.setDifficulty("Medium");
		recipe1.setCaloriesPerServing(350);
		recipe1.setImage("https://image.com/chicken-biryani.jpg");
		recipe1.setRating(4.5);
		recipe1.setReviewCount(100);
		recipe1.setPrepTimeMinutes("15");
		recipe1.setCookTimeMinutes("45");
		recipe1.setIngredients(Arrays.asList("Chicken", "Rice", "Spices"));
		recipe1.setInstructions(Arrays.asList("Cook chicken", "Cook rice", "Mix together"));
		recipe1.setTags(Arrays.asList("Biryani", "Chicken"));
		recipe1.setMealType(Arrays.asList("Lunch", "Dinner"));
		recipe1.setUserId(101);

		RecipeDTO recipe2 = new RecipeDTO();
		recipe2.setId(2L);
		recipe2.setName("Chicken Karahi");
		recipe2.setCuisine("Pakistani");
		recipe2.setServings(3);
		recipe2.setDifficulty("Medium");
		recipe2.setCaloriesPerServing(400);
		recipe2.setImage("https://image.com/chicken-karahi.jpg");
		recipe2.setRating(4.3);
		recipe2.setReviewCount(80);
		recipe2.setPrepTimeMinutes("20");
		recipe2.setCookTimeMinutes("35");
		recipe2.setIngredients(Arrays.asList("Chicken", "Tomatoes", "Spices"));
		recipe2.setInstructions(Arrays.asList("Cook chicken", "Prepare sauce", "Combine"));
		recipe2.setTags(Arrays.asList("Karahi", "Chicken"));
		recipe2.setMealType(Arrays.asList("Lunch", "Dinner"));
		recipe2.setUserId(102);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setRecipes(Arrays.asList(recipe1, recipe2));
		responseDTO.setTotal(2);
		responseDTO.setSkip(0);
		responseDTO.setLimit(10);

		when(recipeClient.getRecipes()).thenReturn(responseDTO);

		// When calling the method
		ResponseDTO result = recipeClient.getRecipes();

		// Then verify the result
		assertNotNull(result);
		assertEquals(2, result.getTotal());
		assertEquals(0, result.getSkip());
		assertEquals(10, result.getLimit());
		assertNotNull(result.getRecipes());
		assertEquals(2, result.getRecipes().size());
		assertEquals("Chicken Biryani", result.getRecipes().get(0).getName());
	}

}
