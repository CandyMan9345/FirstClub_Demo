package com.firstclub.membership.dto;

import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.enums.MembershipTierType;
import lombok.Data;

@Data
public class SubscribeRequest {

	private Long userId;
	private MembershipPlanType planType;
	private MembershipTierType tierType;

}
