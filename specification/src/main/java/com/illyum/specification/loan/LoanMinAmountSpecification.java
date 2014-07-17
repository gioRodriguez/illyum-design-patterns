package com.illyum.specification.loan;

import com.illyum.specification.AbstractSpecification;
import com.illyum.specification.example.Loan;

public class LoanMinAmountSpecification extends AbstractSpecification {
	private final int _minAmount;

	public LoanMinAmountSpecification(int minAmount) {
		_minAmount = minAmount;
	}

	@Override
	public boolean isSatisfiedBy(Object candidate) {
		if (!(candidate instanceof Loan)) {
			return false;
		}

		return _minAmount < ((Loan) candidate).getAmount();
	}

}
