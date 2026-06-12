package com.firstclub.membership.config;

import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.strategy.OrderCountRule;
import com.firstclub.membership.strategy.OrderValueRule;
import com.firstclub.membership.strategy.TierDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class TierConfiguration {

	@Bean
	public List<TierDefinition> tierDefinitions() {
		TierDefinition silver = new TierDefinition(MembershipTierType.SILVER, List.of());

		TierDefinition gold = new TierDefinition(
				MembershipTierType.GOLD,
				List.of(
						new OrderCountRule(5),
						new OrderValueRule(BigDecimal.valueOf(10000))
				)
		);

		TierDefinition platinum = new TierDefinition(
				MembershipTierType.PLATINUM,
				List.of(
						new OrderCountRule(20),
						new OrderValueRule(BigDecimal.valueOf(50000))
				)
		);

		return List.of(silver, gold, platinum);
	}

}
