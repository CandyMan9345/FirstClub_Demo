package com.firstclub.membership.store;

import com.firstclub.membership.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {

	private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

	public User save(User user) {
		users.put(user.getId(), user);
		return user;
	}

	public Optional<User> findById(Long userId) {
		return Optional.ofNullable(users.get(userId));
	}

	public List<User> findAll() {
		return new ArrayList<>(users.values());
	}

	public void delete(Long userId) {
		users.remove(userId);
	}

}
