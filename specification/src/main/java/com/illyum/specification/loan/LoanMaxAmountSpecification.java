package com.illyum.specification.loan;

import com.illyum.specification.AbstractSpecification;
import com.illyum.specification.example.Loan;

public class LoanMaxAmountSpecification extends AbstractSpecification {
	private final int _maxAmount;

	public LoanMaxAmountSpecification(int maxAmount) {
		_maxAmount = maxAmount;
	}

	@Override
	public boolean isSatisfiedBy(Object candidate) {
		if (!(candidate instanceof Loan)) {
			return false;
		}

		return ((Loan) candidate).getAmount() < _maxAmount;
	}

}
