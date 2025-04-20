package com.example.demo.dto;

public class RecipeDropDownDTO {

	private Long id;
	private String name;
	private String cuisine;

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

	public RecipeDropDownDTO(Long id, String name, String cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.cuisine = cuisine;
	}

	public RecipeDropDownDTO() {
		
	}

	
}
