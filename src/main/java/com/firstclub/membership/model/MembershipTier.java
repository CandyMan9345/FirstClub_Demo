package com.firstclub.membership.model;

import com.firstclub.membership.enums.MembershipTierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipTier {

	private Long id;
	private MembershipTierType tierType;
	private List<Benefit> benefits = new ArrayList<>();

}
