package com.firstclub.membership.strategy;

import com.firstclub.membership.model.User;

import java.math.BigDecimal;

public class OrderValueRule implements TierEligibilityRule {

	private final BigDecimal minimumValue;

	public OrderValueRule(BigDecimal minimumValue) {
		this.minimumValue = minimumValue;
	}

	@Override
	public boolean isEligible(User user) {
		return user.getMonthlyOrderValue().compareTo(minimumValue) >= 0;
	}

}
