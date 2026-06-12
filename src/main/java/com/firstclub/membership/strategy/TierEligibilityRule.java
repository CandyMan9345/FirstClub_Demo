package com.firstclub.membership.strategy;

import com.firstclub.membership.model.User;

public interface TierEligibilityRule {

	boolean isEligible(User user);

}
