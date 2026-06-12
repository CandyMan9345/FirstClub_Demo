package com.firstclub.membership.concurrency;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class UserLockManager {

	private final ConcurrentHashMap<Long, ReentrantLock> locks = new ConcurrentHashMap<>();

	public ReentrantLock getLock(Long userId) {
		return locks.computeIfAbsent(userId, id -> new ReentrantLock());
	}

}
