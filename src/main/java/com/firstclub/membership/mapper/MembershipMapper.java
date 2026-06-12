package com.firstclub.membership.mapper;

import com.firstclub.membership.dto.BenefitResponse;
import com.firstclub.membership.dto.MembershipPlanResponse;
import com.firstclub.membership.dto.MembershipSubscriptionResponse;
import com.firstclub.membership.dto.MembershipTierResponse;
import com.firstclub.membership.model.Benefit;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.dto.UserResponse;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembershipMapper {

	public MembershipPlanResponse toPlanResponse(MembershipPlan plan) {
		return new MembershipPlanResponse(
				plan.getId(),
				plan.getType(),
				plan.getPrice(),
				plan.getValidityDays()
		);
	}

	public List<MembershipPlanResponse> toPlanResponses(List<MembershipPlan> plans) {
		return plans.stream().map(this::toPlanResponse).toList();
	}

	public BenefitResponse toBenefitResponse(Benefit benefit) {
		return new BenefitResponse(benefit.getType(), benefit.getValue());
	}

	public MembershipTierResponse toTierResponse(MembershipTier tier) {
		List<BenefitResponse> benefits = tier.getBenefits().stream()
				.map(this::toBenefitResponse)
				.toList();

		return new MembershipTierResponse(tier.getId(), tier.getTierType(), benefits);
	}

	public List<MembershipTierResponse> toTierResponses(List<MembershipTier> tiers) {
		return tiers.stream().map(this::toTierResponse).toList();
	}

	public MembershipSubscriptionResponse toSubscriptionResponse(MembershipSubscription subscription) {
		return new MembershipSubscriptionResponse(
				subscription.getId(),
				subscription.getUserId(),
				subscription.getPlanType(),
				subscription.getTierType(),
				subscription.getStatus(),
				subscription.getStartDate(),
				subscription.getExpiryDate()
		);
	}

	public UserResponse toUserResponse(User user) {
		return new UserResponse(
				user.getId(),
				user.getName(),
				user.getMonthlyOrderCount(),
				user.getMonthlyOrderValue(),
				user.getCohort()
		);
	}

	public List<UserResponse> toUserResponses(List<User> users) {
		return users.stream().map(this::toUserResponse).toList();
	}

}
