package com.book.your.show.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.book.your.show.model.CinemaHall;
import com.book.your.show.model.Screen;

@SuppressWarnings("serial")
public class SearchSpecification implements Specification<Screen> {

	List<SearchCriteria> searchCriteria = new ArrayList<>();

	public void add(String key, Object value, SearchOperation operation) {
		searchCriteria.add(new SearchCriteria(key, value, operation));
	}

	public void setCriteriaValue(String key, Object value, SearchOperation operation) {
		for (SearchCriteria criteria : searchCriteria) {
			System.out.println("key === "+key);
			System.out.println("criteria === "+criteria.toString());
			System.out.println("value === "+value);
			System.out.println("operation === "+operation);
			if (criteria.getKey().equals(key)) {
				criteria.setValue(value);
				return;
			}
		}
		searchCriteria.add(new SearchCriteria(key, value, operation));
	}

	@Override
	public Predicate toPredicate(Root<Screen> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		for (SearchCriteria criteria : searchCriteria) {
			if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
				predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
			}

			if (criteria.getOperation().equals(SearchOperation.JOIN)) {
				if (criteria.getKey().equals("cinemaHall_chCity")) {
					Join<Screen, CinemaHall> cinemaHallJoin = root.join("cinemaHall");
					predicates.add(criteriaBuilder.equal(cinemaHallJoin.<String>get("chCity"), criteria.getValue()));
				}
			}
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
