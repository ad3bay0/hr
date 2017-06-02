/**
 * 
 */
package com.ad3bay0.dev.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad3bay0.dev.hr.model.Role;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Integer> {
	
	Role findByRole(String role);

}
