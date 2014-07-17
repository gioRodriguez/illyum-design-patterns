package com.illyum.specification;

public class AndSpecification extends AbstractSpecification {
	private Specification _one;
	private Specification _other;

	public AndSpecification(Specification one, Specification other) {
		_one = one;
		_other = other;
	}

	@Override
	public boolean isSatisfiedBy(Object candidate) {
		return _one.isSatisfiedBy(candidate) && 
				_other.isSatisfiedBy(candidate);
	}
}
