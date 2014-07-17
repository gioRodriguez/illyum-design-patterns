package com.illyum.specification.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.illyum.specification.Specification;
import com.illyum.specification.loan.SpecificationRepository;

/**
 * This is only demonstrative code :) Have fun
 * 
 * The following code shows you an example of the specification pattern for
 * really good information please refer to Domain-Driven Design: Tackling
 * Complexity in the Heart of Software p. 224 - 241
 */
public class LoanTest {

	private static final int LOAN_AMOUNT = 99;

	@Test
	public void doLoan() {
		// arrange
		boolean expected = true;
		Loan loan = new Loan(LOAN_AMOUNT);

		SpecificationRepository specificationRepository = new SpecificationRepository();
		Specification isValidLoanSpecification = specificationRepository
				.getSpecificationForLoan();

		// act
		boolean actual = isValidLoanSpecification.isSatisfiedBy(loan);

		// assert
		assertEquals("An loan with valid amount :)", expected, actual);
	}

	@Test
	public void doLoanWithAmountLessThanMinAmountAlowed() {
		// arrange
		boolean expected = false;
		Loan loan = new Loan(SpecificationRepository.MIN_AMOUNT - 1);

		SpecificationRepository specificationRepository = new SpecificationRepository();
		Specification isValidLoanSpecification = specificationRepository
				.getSpecificationForLoan();

		// act
		boolean actual = isValidLoanSpecification.isSatisfiedBy(loan);

		// assert
		assertEquals("An loan with amount less that the min allowed", expected,
				actual);
	}

	@Test
	public void doLoanWithAmountGreatestThanMaxAmountAlowed() {
		// arrange
		boolean expected = false;
		Loan loan = new Loan(SpecificationRepository.MAX_AMOUNT + 1);

		SpecificationRepository specificationRepository = new SpecificationRepository();
		Specification isValidLoanSpecification = specificationRepository
				.getSpecificationForLoan();

		// act
		boolean actual = isValidLoanSpecification.isSatisfiedBy(loan);

		// assert
		assertEquals("An loan with amount great that the max allowed", expected,
				actual);
	}

}
