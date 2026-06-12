package com.firstclub.membership.scheduler;

import com.firstclub.membership.enums.MembershipStatus;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.store.MembershipSubscriptionStore;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MembershipExpiryScheduler {

	private final MembershipSubscriptionStore subscriptionStore;

	public MembershipExpiryScheduler(MembershipSubscriptionStore subscriptionStore) {
		this.subscriptionStore = subscriptionStore;
	}

	@Scheduled(fixedRate = 60000)
	public void expireMemberships() {
		for (MembershipSubscription subscription : subscriptionStore.findAll()) {
			if (subscription.getStatus() != MembershipStatus.ACTIVE) {
				continue;
			}

			if (subscription.getExpiryDate().isBefore(LocalDate.now())) {
				subscription.setStatus(MembershipStatus.EXPIRED);
				subscriptionStore.save(subscription);
			}
		}
	}

}
