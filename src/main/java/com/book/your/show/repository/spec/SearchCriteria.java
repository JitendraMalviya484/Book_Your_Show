package com.book.your.show.repository.spec;

public class SearchCriteria {

	 private String key;
	    private Object value;
	    private SearchOperation operation;

	    public SearchCriteria() {
	    }

	    public SearchCriteria(String key, Object value, SearchOperation operation) {
	        this.key = key;
	        this.value = value;
	        this.operation = operation;
	    }

	    public void setValue(Object value) {
	        this.value = value;
	    }

	    public SearchOperation getOperation() {
	        return operation;
	    }

	    public String getKey() {
	        return key;
	    }

	    public Object getValue() {
	        return value;
	    }
}
