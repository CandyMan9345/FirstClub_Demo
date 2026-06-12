package com.firstclub.membership.dto;

import com.firstclub.membership.enums.MembershipPlanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPlanResponse {

	private Long id;
	private MembershipPlanType type;
	private BigDecimal price;
	private int validityDays;

}
