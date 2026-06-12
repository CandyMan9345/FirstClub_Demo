package com.firstclub.membership.model;

import com.firstclub.membership.enums.BenefitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Benefit {

	private BenefitType type;
	private String value;

}
