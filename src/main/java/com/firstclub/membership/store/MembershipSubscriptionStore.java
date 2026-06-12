package com.firstclub.membership.store;

import com.firstclub.membership.model.MembershipSubscription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MembershipSubscriptionStore {

	private final AtomicLong idGenerator = new AtomicLong(1);

	private final ConcurrentHashMap<Long, MembershipSubscription> subscriptions = new ConcurrentHashMap<>();

	private final ConcurrentHashMap<Long, Long> userSubscriptionMapping = new ConcurrentHashMap<>();

	public MembershipSubscription save(MembershipSubscription subscription) {
		if (subscription.getId() == null) {
			subscription.setId(idGenerator.getAndIncrement());
		}

		subscriptions.put(subscription.getId(), subscription);
		userSubscriptionMapping.put(subscription.getUserId(), subscription.getId());

		return subscription;
	}

	public Optional<MembershipSubscription> findByUserId(Long userId) {
		Long subscriptionId = userSubscriptionMapping.get(userId);

		if (subscriptionId == null) {
			return Optional.empty();
		}

		return Optional.ofNullable(subscriptions.get(subscriptionId));
	}

	public List<MembershipSubscription> findAll() {
		return new ArrayList<>(subscriptions.values());
	}

}
