package com.example.demo.dto;

import java.util.List;

public class ResponseDTO {

	private List<RecipeDTO> recipes;
	private int total;
	private int skip;
	private int limit;
	public List<RecipeDTO> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<RecipeDTO> recipes) {
		this.recipes = recipes;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
