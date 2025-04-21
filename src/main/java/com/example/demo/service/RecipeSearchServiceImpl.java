package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.search.engine.search.common.BooleanOperator;
import org.hibernate.search.engine.search.query.SearchQuery;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.client.RecipeClient;
import com.example.demo.config.HibernateSearchInitializer;
import com.example.demo.entity.Recipe;
import com.example.demo.exception.InvalidSearchKeywordException;
import com.example.demo.exception.RecipeNotFoundException;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.mapper.RecipeMapper;
import com.example.demo.dto.RecipeDropDownDTO;
import com.example.demo.dto.ResponseDTO;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class RecipeSearchServiceImpl implements RecipeSearchService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeSearchServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private HibernateSearchInitializer hibernateSearchInitializer;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeMapper recipeMapper;

	@Autowired
	private RecipeClient recipeClient;

	@PostConstruct
	public void loadData() {
		// Retrieve recipes from the RecipeClient
		ResponseDTO responseDTO = recipeClient.getRecipes();

		List<Recipe> recipes = responseDTO.getRecipes().stream().map(recipeMapper::toEntity)
				.collect(Collectors.toList());

		loadRecipes(recipes);
	}

	@Override
	@Transactional
	public void loadRecipes(List<Recipe> recipes) {
		logger.info("📦 Saving {} recipes into the database...", recipes.size());
		recipeRepository.saveAll(recipes);

		// Call HibernateSearchInitializer to trigger indexing after saving recipes
		logger.info("⚙️ Initializing full-text indexing...");
		hibernateSearchInitializer.initialize();
		logger.info("✅ Recipes indexed successfully.");
	}

//	@Override
//	@Transactional
//	public List<RecipeDropDownDTO> searchRecipesByPartialKeyword(String keyword) {
//
//		if (keyword == null || keyword.trim().isEmpty()) {
//			throw new InvalidSearchKeywordException("Search keyword cannot be null or empty");
//		}
//
//		SearchSession searchSession = Search.session(entityManager);
//
//		// Using Wildcard query for partial matching
//		SearchQuery<Recipe> searchQuery = searchSession.search(Recipe.class)
//				.where(f -> f.wildcard().fields("name", "cuisine").matching(keyword + "*")).toQuery();
//
//		List<Recipe> results = searchQuery.fetchAll().hits();
//
//		if (results.isEmpty()) {
//			throw new RecipeNotFoundException("No recipes found for the keyword: " + keyword);
//		}
//
//		return results.stream().map(recipeMapper::toDropDownDto).collect(Collectors.toList());
//	}

	@Override
	@Transactional
	public List<RecipeDropDownDTO> searchRecipesByPartialKeyword(String keyword) {

		if (keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidSearchKeywordException("Search keyword cannot be null or empty");
		}

		SearchSession searchSession = Search.session(entityManager);

		SearchQuery<Recipe> searchQuery = searchSession.search(Recipe.class)
				.where(f -> f.simpleQueryString().fields("name", "cuisine").matching(keyword + "*") // this acts as a
																									// partial match
						.defaultOperator(BooleanOperator.AND)) // ensures all terms in keyword must match somewhere
				.toQuery();

		List<Recipe> results = searchQuery.fetchAll().hits();

		if (results.isEmpty()) {
			throw new RecipeNotFoundException("No recipes found for the keyword: " + keyword);
		}

		return results.stream().map(recipeMapper::toDropDownDto).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<Recipe> getRecipeById(Long id) {
		return recipeRepository.findById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new RecipeNotFoundException("Recipe with ID " + id + " not found"));
	}

}
