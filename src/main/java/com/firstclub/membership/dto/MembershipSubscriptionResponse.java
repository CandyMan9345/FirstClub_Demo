package com.firstclub.membership.dto;

import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.enums.MembershipStatus;
import com.firstclub.membership.enums.MembershipTierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipSubscriptionResponse {

	private Long id;
	private Long userId;
	private MembershipPlanType planType;
	private MembershipTierType tierType;
	private MembershipStatus status;
	private LocalDate startDate;
	private LocalDate expiryDate;

}
