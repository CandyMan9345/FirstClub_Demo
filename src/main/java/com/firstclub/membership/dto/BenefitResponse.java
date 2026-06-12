package com.firstclub.membership.dto;

import com.firstclub.membership.enums.BenefitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BenefitResponse {

	private BenefitType type;
	private String value;

}
