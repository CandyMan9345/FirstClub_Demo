package com.firstclub.membership.store;

import com.firstclub.membership.enums.MembershipTierType;
import com.firstclub.membership.model.MembershipTier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MembershipTierStore {

	private final ConcurrentHashMap<MembershipTierType, MembershipTier> tiers = new ConcurrentHashMap<>();

	public MembershipTier save(MembershipTier tier) {
		tiers.put(tier.getTierType(), tier);
		return tier;
	}

	public Optional<MembershipTier> findByType(MembershipTierType tierType) {
		return Optional.ofNullable(tiers.get(tierType));
	}

	public List<MembershipTier> findAll() {
		return new ArrayList<>(tiers.values());
	}

}
