package com.firstclub.membership.dto;

import com.firstclub.membership.enums.MembershipTierType;
import lombok.Data;

@Data
public class UpdateTierRequest {

	private Long userId;
	private MembershipTierType tierType;

}
