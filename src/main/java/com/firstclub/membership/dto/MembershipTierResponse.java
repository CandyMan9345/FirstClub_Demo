package com.firstclub.membership.dto;

import com.firstclub.membership.enums.MembershipTierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipTierResponse {

	private Long id;
	private MembershipTierType tierType;
	private List<BenefitResponse> benefits;

}
