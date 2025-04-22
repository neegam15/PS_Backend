package com.example.demo.config;

import org.hibernate.search.mapper.orm.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.demo.entity.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class HibernateSearchInitializer {

	private static final Logger logger = LoggerFactory.getLogger(HibernateSearchInitializer.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void initialize() {
		try {
			logger.info("Starting indexing of Recipe entities...");
			Search.session(entityManager).massIndexer(Recipe.class).startAndWait();
			logger.info("Indexing completed successfully.");
		} catch (InterruptedException e) {
			logger.error("Indexing interrupted!", e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			logger.error("Error during indexing!", e);
		}
	}
}