package com.example.demo.entity;

import java.util.List;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
@Indexed
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@FullTextField(analyzer = "standard")
	private String name;

	@FullTextField(analyzer = "standard")
	private String cuisine;

	private String image;

	private int servings;

	private String difficulty;

	private int caloriesPerServing;

	private double rating;

	private int reviewCount;

	private String prepTimeMinutes;

	private String cookTimeMinutes;

	@ElementCollection
	@CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
	@Column(name = "ingredient")
	private List<String> ingredients;

	@ElementCollection
	@CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
	@Column(name = "instruction")
	private List<String> instructions;

	@ElementCollection
	@CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
	@Column(name = "tag")
	private List<String> tags;

	@ElementCollection
	@CollectionTable(name = "recipe_meal_types", joinColumns = @JoinColumn(name = "recipe_id"))
	@Column(name = "meal_type")
	private List<String> mealType;

	private int userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getCaloriesPerServing() {
		return caloriesPerServing;
	}

	public void setCaloriesPerServing(int caloriesPerServing) {
		this.caloriesPerServing = caloriesPerServing;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getPrepTimeMinutes() {
		return prepTimeMinutes;
	}

	public void setPrepTimeMinutes(String prepTimeMinutes) {
		this.prepTimeMinutes = prepTimeMinutes;
	}

	public String getCookTimeMinutes() {
		return cookTimeMinutes;
	}

	public void setCookTimeMinutes(String cookTimeMinutes) {
		this.cookTimeMinutes = cookTimeMinutes;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getMealType() {
		return mealType;
	}

	public void setMealType(List<String> mealType) {
		this.mealType = mealType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Recipe(Long id, String name, String cuisine, String image, int servings, String difficulty,
			int caloriesPerServing, double rating, int reviewCount, String prepTimeMinutes, String cookTimeMinutes,
			List<String> ingredients, List<String> instructions, List<String> tags, List<String> mealType, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.cuisine = cuisine;
		this.image = image;
		this.servings = servings;
		this.difficulty = difficulty;
		this.caloriesPerServing = caloriesPerServing;
		this.rating = rating;
		this.reviewCount = reviewCount;
		this.prepTimeMinutes = prepTimeMinutes;
		this.cookTimeMinutes = cookTimeMinutes;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.tags = tags;
		this.mealType = mealType;
		this.userId = userId;
	}

	public Recipe() {

	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", image=" + image + ", servings="
				+ servings + ", difficulty=" + difficulty + ", caloriesPerServing=" + caloriesPerServing + ", rating="
				+ rating + ", reviewCount=" + reviewCount + ", prepTimeMinutes=" + prepTimeMinutes
				+ ", cookTimeMinutes=" + cookTimeMinutes + ", ingredients=" + ingredients + ", instructions="
				+ instructions + ", tags=" + tags + ", mealType=" + mealType + ", userId=" + userId + "]";
	}

}