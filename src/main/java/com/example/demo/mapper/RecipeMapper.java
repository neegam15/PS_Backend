package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.RecipeDTO;
import com.example.demo.dto.RecipeDropDownDTO;
import com.example.demo.entity.Recipe;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {

	Recipe toEntity(RecipeDTO recipeDTO);

	RecipeDTO toDto(Recipe recipe);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "cuisine", source = "cuisine")
	RecipeDropDownDTO toDropDownDto(Recipe recipe);

}
