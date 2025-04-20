package com.example.demo.dto;

import java.util.List;

public class RecipeDTO {

	private Long id;
	private String name;
	private String cuisine;
	private int servings;
	private String difficulty;
	private int caloriesPerServing;
	private String image;
	private double rating;
	private int reviewCount;
	private String prepTimeMinutes;
	private String cookTimeMinutes;
	private List<String> ingredients;
	private List<String> instructions;
	private List<String> tags;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
}
