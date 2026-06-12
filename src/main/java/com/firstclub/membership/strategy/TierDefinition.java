package com.firstclub.membership.strategy;

import com.firstclub.membership.enums.MembershipTierType;
import lombok.Getter;

import java.util.List;

@Getter
public class TierDefinition {

	private final MembershipTierType tierType;
	private final List<TierEligibilityRule> rules;

	public TierDefinition(MembershipTierType tierType, List<TierEligibilityRule> rules) {
		this.tierType = tierType;
		this.rules = rules;
	}

}
