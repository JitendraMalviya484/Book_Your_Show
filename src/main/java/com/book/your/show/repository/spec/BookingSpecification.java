package com.book.your.show.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.book.your.show.model.Booking;

@SuppressWarnings("serial")
public class BookingSpecification implements Specification<Booking>{
	 List<SearchCriteria> searchCriteria = new ArrayList<>();
	
	 public void add(String key, Object value, SearchOperation operation)   {
	        searchCriteria.add(new SearchCriteria(key, value, operation));
	    }

	@Override
	public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		 List<Predicate> predicates = new ArrayList<>();
		 
		 for(SearchCriteria criteria:searchCriteria) {
			 if(criteria.getOperation().equals(SearchOperation.EQUAL)) {
	                predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
	            }
		 }
		return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
