package com.example.demo;

import java.time.Duration;
import java.util.List;

import org.hibernate.search.engine.search.aggregation.AggregationKey;
import org.hibernate.search.engine.search.query.SearchQuery;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.query.SearchResultTotal;

public abstract class MockSearchQuery<T> implements SearchQuery<T> {

	private final List<T> hits;

	public MockSearchQuery(T... results) {
		this.hits = List.of(results);
	}

	@Override
	public SearchResult<T> fetchAll() {
		return new SearchResult<>() {
			@Override
			public List<T> hits() {
				return hits;
			}

			@Override
			public SearchResultTotal total() {
				return new SearchResultTotal() {
					@Override
					public long hitCount() {
						return hits.size();
					}

					@Override
					public boolean isHitCountExact() {
						return false;
					}

					@Override
					public boolean isHitCountLowerBound() {
						return false;
					}

					@Override
					public long hitCountLowerBound() {
						return 0;
					}
				};
			}

			@Override
			public <A> A aggregation(AggregationKey<A> key) {
				return null;
			}

			@Override
			public Duration took() {
				return Duration.ZERO;
			}

			@Override
			public boolean timedOut() {
				return false;
			}
		};
	}

}
