package com.illyum.specification;

public interface Specification {
 public boolean isSatisfiedBy(Object candidate);
 
 public Specification and(Specification other);
}
