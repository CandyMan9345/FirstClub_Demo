package com.firstclub.membership.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private Long id;
	private String name;
	private int monthlyOrderCount;
	private BigDecimal monthlyOrderValue;
	private String cohort;

}
