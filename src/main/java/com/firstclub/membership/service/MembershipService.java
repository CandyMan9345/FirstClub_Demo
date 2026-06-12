package com.firstclub.membership.service;

import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.User;

import java.util.List;

public interface MembershipService {

	List<MembershipPlan> getPlans();

	List<MembershipTier> getTiers();

	List<User> getUsers();

	MembershipSubscription subscribe(Long userId, MembershipPlanType planType, MembershipTierType tierType);

	MembershipSubscription updateTier(Long userId, MembershipTierType tierType);

	MembershipSubscription getCurrentMembership(Long userId);

	void cancelMembership(Long userId);

}
