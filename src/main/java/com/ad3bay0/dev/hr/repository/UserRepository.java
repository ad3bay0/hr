/**
 * 
 */
package com.ad3bay0.dev.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad3bay0.dev.hr.model.User;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
