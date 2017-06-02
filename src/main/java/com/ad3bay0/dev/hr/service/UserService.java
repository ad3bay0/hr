/**
 * 
 */
package com.ad3bay0.dev.hr.service;

import com.ad3bay0.dev.hr.model.User;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
public interface UserService {
	
	public User findUserByEmail(String email);
	public void saveUser(User user);

}
