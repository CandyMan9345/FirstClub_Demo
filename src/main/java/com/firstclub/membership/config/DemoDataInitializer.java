package com.firstclub.membership.config;

import com.firstclub.membership.enums.BenefitType;
import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.model.Benefit;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.User;
import com.firstclub.membership.store.MembershipPlanStore;
import com.firstclub.membership.store.MembershipTierStore;
import com.firstclub.membership.store.UserStore;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DemoDataInitializer {

	private final UserStore userStore;
	private final MembershipPlanStore planStore;
	private final MembershipTierStore tierStore;

	public DemoDataInitializer(
			UserStore userStore,
			MembershipPlanStore planStore,
			MembershipTierStore tierStore) {

		this.userStore = userStore;
		this.planStore = planStore;
		this.tierStore = tierStore;
	}

	@PostConstruct
	public void init() {
		seedUsers();
		seedPlans();
		seedTiers();
	}

	private void seedUsers() {
		userStore.save(new User(1L, "Alice", 2, BigDecimal.valueOf(3000), "NEW"));
		userStore.save(new User(2L, "Bob", 8, BigDecimal.valueOf(15000), "REGULAR"));
		userStore.save(new User(3L, "Carol", 25, BigDecimal.valueOf(60000), "VIP"));
	}

	private void seedPlans() {
		planStore.save(new MembershipPlan(1L, MembershipPlanType.MONTHLY, BigDecimal.valueOf(299), 30));
		planStore.save(new MembershipPlan(2L, MembershipPlanType.QUARTERLY, BigDecimal.valueOf(699), 90));
		planStore.save(new MembershipPlan(3L, MembershipPlanType.YEARLY, BigDecimal.valueOf(1999), 365));
	}

	private void seedTiers() {
		tierStore.save(new MembershipTier(
				1L,
				MembershipTierType.SILVER,
				List.of(new Benefit(BenefitType.FREE_DELIVERY, "true"))
		));

		tierStore.save(new MembershipTier(
				2L,
				MembershipTierType.GOLD,
				List.of(
						new Benefit(BenefitType.FREE_DELIVERY, "true"),
						new Benefit(BenefitType.EXTRA_DISCOUNT, "5")
				)
		));

		tierStore.save(new MembershipTier(
				3L,
				MembershipTierType.PLATINUM,
				List.of(
						new Benefit(BenefitType.FREE_DELIVERY, "true"),
						new Benefit(BenefitType.EXTRA_DISCOUNT, "10"),
						new Benefit(BenefitType.PRIORITY_SUPPORT, "true"),
						new Benefit(BenefitType.EARLY_ACCESS_SALE, "true")
				)
		));
	}

}
