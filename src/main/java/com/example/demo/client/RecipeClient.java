package com.example.demo.client;

import java.util.concurrent.TimeoutException;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpServerErrorException;

import com.example.demo.dto.ResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "recipeClient", url = "${recipe.api.url}")
public interface RecipeClient {

	@GetMapping
	@Retry(name = "recipeClientRetry", fallbackMethod = "fallbackRecipe")
	@CircuitBreaker(name = "recipeClientCircuitBreaker", fallbackMethod = "fallbackRecipe")
	ResponseDTO getRecipes();

	// Fallback method in case of failure
	default ResponseDTO fallbackRecipe(Throwable t) {
		if (t instanceof TimeoutException) {
			System.out.println("Timeout occurred: " + t.getMessage());
		} else if (t instanceof HttpServerErrorException) {
			System.out.println("Server error: " + t.getMessage());
		} else {
			System.out.println("Fallback triggered due to: " + t.getMessage());
		}
		return new ResponseDTO();
	}
}