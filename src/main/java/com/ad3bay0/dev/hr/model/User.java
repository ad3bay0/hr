/**
 * 
 */
package com.ad3bay0.dev.hr.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Email(message="*Please provide a valid email")
	@NotEmpty(message="*Please provide an email")
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	@Length(message="*Your password must have at least 5 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;
	
	@Column(name="first_name")
	@NotEmpty(message="*Please provide your first name")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message="*Please provide your last name")
	private String lastName;
	
	@Column(name="active")
	private int active;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
