# Recipe Search API (Spring Boot)

A Spring Boot application that loads recipe data from an external public API, stores it in an H2 in-memory database, 
and supports full-text search using Hibernate Search with Lucene. It exposes REST APIs for searching recipes (typeahead-friendly) 
and viewing recipe details.

----------------------------------------------------------------------------------------------------------------------------------------

## Features

- Fetches and stores recipes from `https://dummyjson.com/recipes`
- Automatically indexes recipes using Hibernate Search (Lucene)
- Exposes REST endpoints for:
  - Free text search for Recipe Name and Cuisine
  - Fetching full recipe details by ID
- Uses `@PostConstruct` to load and index recipes on startup
- Follows modular design, clean code, and layered architecture
- Centralized exception handling
- Logging using SLF4J/Logback
- Unit tests with JUnit & Mockito
- API docs with Swagger/OpenAPI 3.0

----------------------------------------------------------------------------------------------------------------------------------------

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate Search (Lucene)
- Spring Cloud OpenFeign
- H2 Database (In-Memory)
- JUnit 5, Mockito
- Swagger / OpenAPI
- Maven

----------------------------------------------------------------------------------------------------------------------------------------

## How to Build and Run

### Prerequisites

- Java 17+
- Maven

### Steps

- Download it from the shared Google Drive link.

- Open the project in your IDE(Eg:Spring Tool Suite).

	Directly run the `RecipeSearchApiApplication.java` from your IDE.

- Run the application using:

	mvn clean package - This will create a .jar file in the target/ folder.

	java -jar target/your-app-name.jar

- On startup, the app will fetch recipes from the external API and index them.

----------------------------------------------------------------------------------------------------------------------------------------

## API Endpoints

### **Search Recipes (Typeahead)**

	- GET:- `/api/recipes/searchPartial?query={recipe name or cuisine}`
	- Description:- Searches recipes by name or cuisine with partial match (wildcard) based on the indexes created earlier using lucene.
	- Response:-

```json
[
    {
        "id": 12,
        "name": "Chicken Karahi",
        "cuisine": "Pakistani"
    }
]
```

### **Get Recipe by ID**

	- GET:- `/api/recipes/{id}`
	- Description:- Fetches full details of a recipe by its ID.
	- Response:-

```json
{
  "id": 1,
  "name": "Chicken Biryani",
  "cuisine": "Indian",
  "image": "https://cdn.example.com/images/biryani.jpg",
  "ingredients": ["Chicken", "Rice", "Spices"],
  "instructions": "Cook rice and chicken separately..."
}
```

----------------------------------------------------------------------------------------------------------------------------------------

## Testing

 Run all tests using:

	mvn test

	Or using IDE run the test classes as Run As -> JUnit Test
----------------------------------------------------------------------------------------------------------------------------------------

## Swagger API Docs

Once the app is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

----------------------------------------------------------------------------------------------------------------------------------------

## Future Improvements

- Migrate DB to PostgreSQL or MySQL for production