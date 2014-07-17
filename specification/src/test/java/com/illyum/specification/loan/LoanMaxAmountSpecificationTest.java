package com.illyum.specification.loan;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.illyum.specification.example.Loan;

public class LoanMaxAmountSpecificationTest {

	private static final int LOAN_AMOUNT = 150;

	@Test
	public void isSatisfiedBy() {
		// arrange
		Loan loan = new Loan(LOAN_AMOUNT);		
		
		// act
		LoanMaxAmountSpecification maxAmountSpecification = new LoanMaxAmountSpecification(LOAN_AMOUNT + 1);
		
		// assert
		assertTrue(maxAmountSpecification.isSatisfiedBy(loan));
	}
	
	@Test
	public void isSatisfiedByMustBeFalse() {
		// arrange
		Loan loan = new Loan(LOAN_AMOUNT);		
		
		// act
		LoanMaxAmountSpecification maxAmountSpecification = new LoanMaxAmountSpecification(LOAN_AMOUNT);
		
		// assert
		assertFalse(maxAmountSpecification.isSatisfiedBy(loan));
	}

}
