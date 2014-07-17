package com.illyum.specification.loan;

import com.illyum.specification.Specification;

/**
 * Currently the loan Specification is constrained by max and min amounts and
 * those values are hard coded, so in a real life example those values must be
 * retrieved from some repository
 * 
 */
public class SpecificationRepository {
	public static final int MIN_AMOUNT = 50;
	public static final int MAX_AMOUNT = 100;

	/**
	 * Creates the specification needed for valid a Loan Maybe a worthy argument
	 * for this function could be the center but for demonstrative purposes this is
	 * good enough :)
	 * 
	 * @return Specification for loan
	 */
	public Specification getSpecificationForLoan() {
		LoanMaxAmountSpecification maxAmountSpecification = new LoanMaxAmountSpecification(
				MAX_AMOUNT
		);
		LoanMinAmountSpecification minAmmountSpecification = new LoanMinAmountSpecification(
				MIN_AMOUNT
		);

		return maxAmountSpecification.and(minAmmountSpecification);
	}
}
