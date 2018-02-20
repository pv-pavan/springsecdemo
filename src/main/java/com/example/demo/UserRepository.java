package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pv on 19/2/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
