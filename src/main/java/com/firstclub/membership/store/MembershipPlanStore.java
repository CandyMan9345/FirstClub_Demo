package com.firstclub.membership.store;

import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.model.MembershipPlan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MembershipPlanStore {

	private final ConcurrentHashMap<MembershipPlanType, MembershipPlan> plans = new ConcurrentHashMap<>();

	public MembershipPlan save(MembershipPlan plan) {
		plans.put(plan.getType(), plan);
		return plan;
	}

	public List<MembershipPlan> findAll() {
		return new ArrayList<>(plans.values());
	}

	public Optional<MembershipPlan> findByType(MembershipPlanType type) {
		return Optional.ofNullable(plans.get(type));
	}

}
