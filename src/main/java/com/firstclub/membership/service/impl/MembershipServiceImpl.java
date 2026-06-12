package com.firstclub.membership.service.impl;

import com.firstclub.membership.concurrency.UserLockManager;
import com.firstclub.membership.enums.MembershipPlanType;
import com.firstclub.membership.enums.MembershipStatus;
import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.exception.MembershipException;
import com.firstclub.membership.exception.ResourceNotFoundException;
import com.firstclub.membership.model.MembershipPlan;
import com.firstclub.membership.model.MembershipSubscription;
import com.firstclub.membership.model.MembershipTier;
import com.firstclub.membership.model.User;
import com.firstclub.membership.service.MembershipService;
import com.firstclub.membership.store.MembershipPlanStore;
import com.firstclub.membership.store.MembershipSubscriptionStore;
import com.firstclub.membership.store.MembershipTierStore;
import com.firstclub.membership.store.UserStore;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Service
public class MembershipServiceImpl implements MembershipService {

	private final UserStore userStore;
	private final MembershipPlanStore planStore;
	private final MembershipTierStore tierStore;
	private final MembershipSubscriptionStore subscriptionStore;
	private final UserLockManager lockManager;

	public MembershipServiceImpl(
			UserStore userStore,
			MembershipPlanStore planStore,
			MembershipTierStore tierStore,
			MembershipSubscriptionStore subscriptionStore,
			UserLockManager lockManager) {

		this.userStore = userStore;
		this.planStore = planStore;
		this.tierStore = tierStore;
		this.subscriptionStore = subscriptionStore;
		this.lockManager = lockManager;
	}

	@Override
	public List<MembershipPlan> getPlans() {
		return planStore.findAll();
	}

	@Override
	public List<MembershipTier> getTiers() {
		return tierStore.findAll();
	}

	@Override
	public List<User> getUsers() {
		return userStore.findAll();
	}

	@Override
	public MembershipSubscription subscribe(Long userId, MembershipPlanType planType, MembershipTierType tierType) {
		Lock lock = lockManager.getLock(userId);

		lock.lock();

		try {
			User user = userStore.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found"));

			MembershipPlan plan = planStore.findByType(planType)
					.orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

			tierStore.findByType(tierType)
					.orElseThrow(() -> new ResourceNotFoundException("Tier not found"));

			subscriptionStore.findByUserId(userId)
					.ifPresent(subscription -> {
						if (subscription.getStatus() == MembershipStatus.ACTIVE) {
							throw new MembershipException("Active subscription already exists");
						}
					});

			MembershipSubscription subscription = new MembershipSubscription();

			subscription.setUserId(user.getId());
			subscription.setPlanType(plan.getType());
			subscription.setTierType(tierType);
			subscription.setStatus(MembershipStatus.ACTIVE);

			LocalDate startDate = LocalDate.now();

			subscription.setStartDate(startDate);
			subscription.setExpiryDate(startDate.plusDays(plan.getValidityDays()));

			return subscriptionStore.save(subscription);

		} finally {
			lock.unlock();
		}
	}

	@Override
	public MembershipSubscription updateTier(Long userId, MembershipTierType tierType) {
		Lock lock = lockManager.getLock(userId);

		lock.lock();

		try {
			MembershipSubscription subscription = subscriptionStore.findByUserId(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));

			if (subscription.getStatus() != MembershipStatus.ACTIVE) {
				throw new MembershipException("Membership is not active");
			}

			tierStore.findByType(tierType)
					.orElseThrow(() -> new ResourceNotFoundException("Tier not found"));

			subscription.setTierType(tierType);

			return subscriptionStore.save(subscription);

		} finally {
			lock.unlock();
		}
	}

	@Override
	public MembershipSubscription getCurrentMembership(Long userId) {
		return subscriptionStore.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
	}

	@Override
	public void cancelMembership(Long userId) {
		Lock lock = lockManager.getLock(userId);

		lock.lock();

		try {
			MembershipSubscription subscription = subscriptionStore.findByUserId(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));

			subscription.setStatus(MembershipStatus.CANCELLED);
			subscriptionStore.save(subscription);

		} finally {
			lock.unlock();
		}
	}

}
