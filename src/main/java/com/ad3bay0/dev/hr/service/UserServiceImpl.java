/**
 * 
 */
package com.ad3bay0.dev.hr.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ad3bay0.dev.hr.model.Role;
import com.ad3bay0.dev.hr.model.User;
import com.ad3bay0.dev.hr.repository.RoleRepository;
import com.ad3bay0.dev.hr.repository.UserRepository;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		userRepository.save(user);

	}

}
