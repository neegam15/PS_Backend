package com.example.demo.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.projection.dsl.SearchProjectionFactory;
import org.hibernate.search.engine.search.query.SearchQuery;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.query.dsl.SearchQueryOptionsStep;
import org.hibernate.search.engine.search.query.dsl.SearchQuerySelectStep;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.common.EntityReference;
import org.hibernate.search.mapper.orm.search.loading.dsl.SearchLoadingOptionsStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.example.demo.client.RecipeClient;
import com.example.demo.config.HibernateSearchInitializer;
import com.example.demo.dto.RecipeDropDownDTO;
import com.example.demo.entity.Recipe;
import com.example.demo.exception.InvalidSearchKeywordException;
import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.mapper.RecipeMapper;
import com.example.demo.repository.RecipeRepository;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RecipeSearchServiceImplTest {

	@Mock
	private RecipeRepository recipeRepository;

	@InjectMocks
	private RecipeSearchServiceImpl recipeSearchService;

	@Mock
	private RecipeMapper recipeMapper;

	@Mock
	private RecipeClient recipeClient;

	@Mock
	private HibernateSearchInitializer hibernateSearchInitializer;

	@Mock
	private EntityManager entityManager;

	@Test
	void getRecipeById_ExistingId_ReturnsRecipe() {
		Long id = 1L;
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setName("Classic Margherita Pizza");
		recipe.setCuisine("Italian");
		recipe.setImage("https://cdn.dummyjson.com/recipe-images/1.webp");
		recipe.setServings(4);
		recipe.setDifficulty("Easy");
		recipe.setCaloriesPerServing(300);
		recipe.setRating(4.6);
		recipe.setReviewCount(98);
		recipe.setPrepTimeMinutes("20");
		recipe.setCookTimeMinutes("15");
		recipe.setIngredients(Arrays.asList("Pizza dough", "Tomato sauce", "Fresh mozzarella cheese",
				"Fresh basil leaves", "Olive oil", "Salt and pepper to taste"));
		recipe.setInstructions(Arrays.asList("Preheat the oven to 475°F (245°C).",
				"Roll out the pizza dough and spread tomato sauce evenly.",
				"Top with slices of fresh mozzarella and fresh basil leaves.",
				"Drizzle with olive oil and season with salt and pepper.",
				"Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
				"Slice and serve hot."));
		recipe.setTags(Arrays.asList("Pizza", "Italian"));
		recipe.setMealType(Arrays.asList("Dinner"));
		recipe.setUserId(166);

		when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

		ResponseEntity<Recipe> response = recipeSearchService.getRecipeById(id);

		assertEquals(1, response.getBody().getId());
		assertEquals("Classic Margherita Pizza", response.getBody().getName());
		assertEquals("Italian", response.getBody().getCuisine());
		assertEquals("https://cdn.dummyjson.com/recipe-images/1.webp", response.getBody().getImage());
		assertEquals(4, response.getBody().getServings());
		assertEquals("Easy", response.getBody().getDifficulty());
		assertEquals(300, response.getBody().getCaloriesPerServing());
		assertEquals(4.6, response.getBody().getRating());
		assertEquals(98, response.getBody().getReviewCount());
		assertEquals("20", response.getBody().getPrepTimeMinutes());
		assertEquals("15", response.getBody().getCookTimeMinutes());
		assertEquals(Arrays.asList("Pizza dough", "Tomato sauce", "Fresh mozzarella cheese", "Fresh basil leaves",
				"Olive oil", "Salt and pepper to taste"), response.getBody().getIngredients());
		assertEquals(Arrays.asList("Preheat the oven to 475°F (245°C).",
				"Roll out the pizza dough and spread tomato sauce evenly.",
				"Top with slices of fresh mozzarella and fresh basil leaves.",
				"Drizzle with olive oil and season with salt and pepper.",
				"Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
				"Slice and serve hot."), response.getBody().getInstructions());
		assertEquals(Arrays.asList("Pizza", "Italian"), response.getBody().getTags());
		assertEquals(Arrays.asList("Dinner"), response.getBody().getMealType());
		assertEquals(166, response.getBody().getUserId());
	}

	@Test
	void getRecipeById_NotFound_ThrowsException() {
		Long id = 99L;

		when(recipeRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(RecipeNotFoundException.class, () -> recipeSearchService.getRecipeById(id));
	}

//	@Test
//	void searchRecipesByPartialKeywordReturnsResultsSuccessfully() {
//		String keyword = "piz";
//
//		// Sample Recipe
//		Recipe recipe = new Recipe();
//		recipe.setId(1L);
//		recipe.setName("Pizza Napoli");
//		recipe.setCuisine("Italian");
//		List<Recipe> recipes = List.of(recipe);
//
//		// Mocking components
//		Session session = mock(Session.class);
//		SearchSession searchSession = mock(SearchSession.class);
//		SearchQuery<Recipe> searchQuery = mock(SearchQuery.class);
//		SearchResult<Recipe> searchResult = mock(SearchResult.class);
//
//		// Mock static Search.session
//		try (MockedStatic<Search> staticMock = mockStatic(Search.class)) {
//			staticMock.when(() -> Search.session(session)).thenReturn(searchSession);
//
//			// Mocks for EntityManager
//			when(entityManager.unwrap(Session.class)).thenReturn(session);
//
//			// Mock SearchSession behavior
//			when(searchSession.search(Recipe.class)).thenReturn((SearchQuerySelectStep<SearchQueryOptionsStep<?, Recipe, SearchLoadingOptionsStep, ?, ?>, EntityReference, Recipe, SearchLoadingOptionsStep, SearchProjectionFactory<EntityReference, Recipe>, SearchPredicateFactory>) searchQuery);
//			when(searchQuery.fetchAll()).thenReturn(searchResult);
//
//			// Return search results
//			when(searchResult.hits()).thenReturn(recipes);
//			when(recipeMapper.toDropDownDto(recipe)).thenReturn(new RecipeDropDownDTO(1L, "Pizza Napoli", "Italian"));
//
//			// Call service
//			List<RecipeDropDownDTO> result = recipeSearchService.searchRecipesByPartialKeyword(keyword);
//
//			// Assertions
//			assertEquals(1, result.size());
//			assertEquals("Pizza Napoli", result.get(0).getName());
//		}
//	}

	//
	@Test
	void searchRecipesByPartialKeywordThrowsExceptionWhenKeywordIsNull() {
		assertThrows(InvalidSearchKeywordException.class,
				() -> recipeSearchService.searchRecipesByPartialKeyword(null));
	}

//
	@Test
	void searchRecipesByPartialKeywordThrowsExceptionWhenKeywordIsEmpty() {
		assertThrows(InvalidSearchKeywordException.class, () -> recipeSearchService.searchRecipesByPartialKeyword(" "));
	}

//
//	@SuppressWarnings("deprecation")
//	@Test
//	void searchRecipesByPartialKeywordThrowsExceptionWhenNoResultsFound() {
//	    String keyword = "nonexistent";
//	    SearchSession searchSession = mock(SearchSession.class);
//	    Session session = mock(Session.class); // Mock the Session object
//	    SessionFactory sessionFactory = mock(SessionFactory.class); // Mock the SessionFactory object
//	    SearchQuerySelectStep<?, EntityReference, Recipe, SearchLoadingOptionsStep, SearchProjectionFactory<EntityReference, Recipe>, SearchPredicateFactory> selectStep = mock(SearchQuerySelectStep.class);
//	    SearchQuery<Recipe> searchQuery = mock(SearchQuery.class);
//	    SearchResult<Recipe> searchResult = mock(SearchResult.class);
//
//	    // Ensure the sessionFactory is properly mocked
//	    when(session.getSessionFactory()).thenReturn(sessionFactory);
//	    doReturn(session).when(entityManager).unwrap(Session.class); // Return the mock Session object
//	    doReturn(searchSession).when(entityManager).unwrap(SearchSession.class); // Return the mock SearchSession object
//	    doReturn(selectStep).when(searchSession).search(Recipe.class);
//	    doReturn(searchResult).when(searchQuery).fetchAll();
//	    doReturn(List.of()).when(searchResult).hits();
//
//	    assertThrows(RecipeNotFoundException.class, () -> recipeSearchService.searchRecipesByPartialKeyword(keyword));
//	}

//	@SuppressWarnings("deprecation")
//	@Test
//	void searchRecipesByPartialKeywordThrowsExceptionWhenNoResultsFound() {
//	    String keyword = "nonexistent";
//	    SearchSession searchSession = mock(SearchSession.class);
//	    SearchQuerySelectStep<SearchQueryOptionsStep<?, Recipe, SearchLoadingOptionsStep, ?, ?>, EntityReference, Recipe, SearchLoadingOptionsStep, SearchProjectionFactory<EntityReference, Recipe>, SearchPredicateFactory> selectStep = mock(SearchQuerySelectStep.class);
//	    SearchQueryOptionsStep<?, Recipe, SearchLoadingOptionsStep, ?, ?> optionsStep = mock(SearchQueryOptionsStep.class);
//	    SearchQuery<Recipe> searchQuery = mock(SearchQuery.class);
//	    SearchResult<Recipe> searchResult = mock(SearchResult.class);
//	    SearchPredicate searchPredicate = mock(SearchPredicate.class);
//
//	    when(entityManager.unwrap(SearchSession.class)).thenReturn(searchSession);
//	    when(searchSession.search(Recipe.class)).thenReturn(selectStep);
//	    when(selectStep.where(searchPredicate)).thenReturn((SearchQueryOptionsStep<?, Recipe, SearchLoadingOptionsStep, ?, ?>) optionsStep);
//	    when(optionsStep.toQuery()).thenReturn(searchQuery);
//	    when(searchQuery.fetchAll()).thenReturn(searchResult);
//	    when(searchResult.hits()).thenReturn(List.of());
//
//	    assertThrows(RecipeNotFoundException.class, () -> recipeSearchService.searchRecipesByPartialKeyword(keyword));
//	}

}
