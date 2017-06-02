/**
 * 
 */
package com.ad3bay0.dev.hr.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ad3bay0.dev.hr.model.Role;
import com.ad3bay0.dev.hr.model.User;
import com.ad3bay0.dev.hr.repository.RoleRepository;
import com.ad3bay0.dev.hr.repository.UserRepository;

/**
 * @author Adebayo Adeniyan 1 Jun 2017
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EntityManager entityManager;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		User user = (User)entityManager.createQuery("select u from User where u.username = ?1 join fetch u.roles").setParameter(1, username).getSingleResult();

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role r : user.getRoles()) {

			GrantedAuthority authority = new SimpleGrantedAuthority(r.getRole());

			authorities.add(authority);

		}

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), authorities);

		return userDetails;

	}

}
