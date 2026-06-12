package com.firstclub.membership.controller;

import com.firstclub.membership.dto.CancelMembershipResponse;
import com.firstclub.membership.dto.MembershipPlanResponse;
import com.firstclub.membership.dto.MembershipSubscriptionResponse;
import com.firstclub.membership.dto.MembershipTierResponse;
import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.dto.UserResponse;
import com.firstclub.membership.mapper.MembershipMapper;
import com.firstclub.membership.service.MembershipService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

	private final MembershipService membershipService;
	private final MembershipMapper membershipMapper;

	public MembershipController(MembershipService membershipService, MembershipMapper membershipMapper) {
		this.membershipService = membershipService;
		this.membershipMapper = membershipMapper;
	}

	@GetMapping("/users")
	public List<UserResponse> getUsers() {
		return membershipMapper.toUserResponses(membershipService.getUsers());
	}

	@GetMapping("/plans")
	public List<MembershipPlanResponse> getPlans() {
		return membershipMapper.toPlanResponses(membershipService.getPlans());
	}

	@GetMapping("/tiers")
	public List<MembershipTierResponse> getTiers() {
		return membershipMapper.toTierResponses(membershipService.getTiers());
	}

	@PostMapping("/subscribe")
	public MembershipSubscriptionResponse subscribe(@RequestBody SubscribeRequest request) {
		return membershipMapper.toSubscriptionResponse(
				membershipService.subscribe(request.getUserId(), request.getPlanType(), request.getTierType())
		);
	}

	@PutMapping("/tier")
	public MembershipSubscriptionResponse updateTier(@RequestBody UpdateTierRequest request) {
		return membershipMapper.toSubscriptionResponse(
				membershipService.updateTier(request.getUserId(), request.getTierType())
		);
	}

	@GetMapping("/{userId}")
	public MembershipSubscriptionResponse getMembership(@PathVariable Long userId) {
		return membershipMapper.toSubscriptionResponse(
				membershipService.getCurrentMembership(userId)
		);
	}

	@DeleteMapping("/{userId}")
	public CancelMembershipResponse cancelMembership(@PathVariable Long userId) {
		membershipService.cancelMembership(userId);
		return new CancelMembershipResponse("Membership cancelled");
	}

}
