package com.illyum.specification;

public abstract class AbstractSpecification implements Specification {

	@Override
	public Specification and(Specification other) {
		return new AndSpecification(this, other);
	}

}
