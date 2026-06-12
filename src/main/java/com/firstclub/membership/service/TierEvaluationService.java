package com.firstclub.membership.service;

import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.model.User;

public interface TierEvaluationService {

	MembershipTierType evaluate(User user);

}
