package com.firstclub.membership.strategy;

import com.firstclub.membership.model.User;

public class OrderCountRule implements TierEligibilityRule {

	private final int minimumOrders;

	public OrderCountRule(int minimumOrders) {
		this.minimumOrders = minimumOrders;
	}

	@Override
	public boolean isEligible(User user) {
		return user.getMonthlyOrderCount() >= minimumOrders;
	}

}
