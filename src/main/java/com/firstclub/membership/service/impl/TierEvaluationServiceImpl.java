package com.firstclub.membership.service.impl;

import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.model.User;
import com.firstclub.membership.service.TierEvaluationService;
import com.firstclub.membership.strategy.TierDefinition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TierEvaluationServiceImpl implements TierEvaluationService {

	private final List<TierDefinition> tierDefinitions;

	public TierEvaluationServiceImpl(List<TierDefinition> tierDefinitions) {
		this.tierDefinitions = tierDefinitions;
	}

	@Override
	public MembershipTierType evaluate(User user) {
		MembershipTierType result = MembershipTierType.SILVER;

		for (TierDefinition definition : tierDefinitions) {
			boolean eligible = definition.getRules()
					.stream()
					.allMatch(rule -> rule.isEligible(user));

			if (eligible) {
				result = definition.getTierType();
			}
		}

		return result;
	}

}
