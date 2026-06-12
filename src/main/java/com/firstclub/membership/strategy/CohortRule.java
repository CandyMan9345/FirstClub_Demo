package com.firstclub.membership.strategy;

import com.firstclub.membership.model.User;

public class CohortRule implements TierEligibilityRule {

	private final String cohort;

	public CohortRule(String cohort) {
		this.cohort = cohort;
	}

	@Override
	public boolean isEligible(User user) {
		return cohort.equalsIgnoreCase(user.getCohort());
	}

}
